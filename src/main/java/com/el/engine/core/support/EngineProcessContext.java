package com.el.engine.core.support;

import com.el.engine.core.config.SceneConfiguration;
import com.el.engine.core.handler.process.StandardProcess;
import com.el.engine.utils.UncheckCastUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 引擎流程上下文 <br/>
 * since 2021/5/30
 *
 * @author eddie.lys
 */
@Slf4j
public class EngineProcessContext {
    /**
     * 引擎流程定义缓存<场景, 场景定义>
     */
    private static final Map<String, SceneConfiguration<?, ?>> ENGINE_PROCESS_CACHE = new ConcurrentHashMap<>(16);
    /**
     * 标准流程对象<类名, 标准流程定义实例>
     */
    private static final Map<String, StandardProcess<Object, Object>> STANDARD_PROCESS_OBJECT_CACHE = new ConcurrentHashMap<>(64);
    /**
     * 标准流程定义<场景, 标准流程定义实例>
     */
    private static final Map<String, List<StandardProcess<Object, Object>>> STANDARD_PROCESS_DEFINE_CACHE = new ConcurrentHashMap<>(16);

    private static final Object LOCK = new Object();

    private static ConfigurableApplicationContext configurableApplicationContext;

    private static BeanDefinitionRegistry beanDefinitionRegistry;

    public static void setBeanDefinitionRegistry(@NonNull ConfigurableApplicationContext beanDefinitionRegistry) {
        EngineProcessContext.configurableApplicationContext = beanDefinitionRegistry;
        EngineProcessContext.beanDefinitionRegistry = (BeanDefinitionRegistry) beanDefinitionRegistry.getBeanFactory();

    }

    public static void addEngineProcessDefine(SceneConfiguration<?, ?> sceneConfiguration) {
        boolean lazyLoadingModel = sceneConfiguration.getLazyLoadingIfPresent();
        log.info("MLE - init scene {}, lazy model: {}", sceneConfiguration.getScene(), lazyLoadingModel ? "lazy" : "init");
        if (Objects.nonNull(ENGINE_PROCESS_CACHE.get(sceneConfiguration.getScene()))) {
            throw new RuntimeException("MLE - init scene " + sceneConfiguration.getScene() + " error, process definition conflict");
        }
        // 放入一级缓存
        ENGINE_PROCESS_CACHE.put(sceneConfiguration.getScene(), sceneConfiguration);

        if (lazyLoadingModel) {
            return;
        }
        // 初始化场景流程
        initStandardProcess(sceneConfiguration);
    }

    public static List<StandardProcess<Object, Object>> getEngineProcessDefine(String scene) {
        SceneConfiguration<?, ?> sceneConfiguration = ENGINE_PROCESS_CACHE.get(scene);
        if (Objects.isNull(sceneConfiguration)) {
            throw new RuntimeException("MLE - un_know scene define error");
        }
        boolean lazyLoading = sceneConfiguration.getLazyLoadingIfPresent();
        if (!lazyLoading) {
            // 非懒加载模式必定存在
            return STANDARD_PROCESS_DEFINE_CACHE.get(scene);
        }
        // 如果当前场景不存在，且场景定义为懒加载模式，则判定是否需要初始化
        if (Objects.isNull(STANDARD_PROCESS_DEFINE_CACHE.get(scene))) {
            // 理论上锁不会被升级，维持轻量级锁
            synchronized (LOCK) {
                // 防止多线程同时触发初始化同一个流程
                initStandardProcess(sceneConfiguration);
            }
        }
        return STANDARD_PROCESS_DEFINE_CACHE.get(scene);
    }

    private static void initStandardProcess(SceneConfiguration<?, ?> sceneConfiguration) {
        List<String> sceneProcessList = sceneConfiguration.getSceneProcessList();
        // 入场景流程定义为空，则填补当前流程定义执行流程为空
        if (CollectionUtils.isEmpty(sceneProcessList)) {
            STANDARD_PROCESS_DEFINE_CACHE.put(sceneConfiguration.getScene(), new ArrayList<>());
        }
        List<StandardProcess<Object, Object>> standardProcessList = new ArrayList<>();
        for (String className : sceneProcessList) {
            // 判断是否已经加载该类
            if (Objects.nonNull(STANDARD_PROCESS_OBJECT_CACHE.get(className))) {
                standardProcessList.add(STANDARD_PROCESS_OBJECT_CACHE.get(className));
                continue;
            }
            try {
                Class<?> sceneProcessClass = Class.forName(className);
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(sceneProcessClass);
                beanDefinitionRegistry.registerBeanDefinition(sceneConfiguration.getScene() + sceneProcessClass.getName(),
                        beanDefinitionBuilder.getBeanDefinition());

                StandardProcess<Object, Object> sceneProcess =
                        UncheckCastUtil.castUncheckedObject(configurableApplicationContext.getBean(sceneConfiguration.getScene() + sceneProcessClass.getName()));
                STANDARD_PROCESS_OBJECT_CACHE.put(className, sceneProcess);
                standardProcessList.add(sceneProcess);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("MLE - init standard process error", e);
            }
        }
        // 插入场景流程定义
        STANDARD_PROCESS_DEFINE_CACHE.put(sceneConfiguration.getScene(), standardProcessList);
    }

}
