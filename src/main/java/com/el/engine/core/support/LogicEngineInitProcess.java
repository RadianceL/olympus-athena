package com.el.engine.core.support;

import com.el.engine.core.data.EngineApplicationSystem;
import com.el.engine.core.data.SceneConfiguration;
import com.el.engine.core.support.annotations.SceneProcess;
import com.el.engine.core.support.annotations.SceneProcessTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 逻辑引擎初始化控制 <br/>
 * since 2021/5/18
 *
 * @author eddie.lys
 */
@Order(Integer.MIN_VALUE)
public class LogicEngineInitProcess implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        Class<?>[] sceneProcessTemplateClasses = EngineApplicationSystem.getSceneProcessTemplateClasses();
        initSceneProcessTemplate(sceneProcessTemplateClasses);
    }

    private void initSceneProcessTemplate(Class<?>[] sceneProcessTemplateClasses) {
        for (Class<?> targetClass : sceneProcessTemplateClasses) {
            Object bean;
            try {
                bean = targetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                // 实例化场景流程配置类异常，阻断启动
                throw new RuntimeException("MLE - Calling scenario processing scheme configuration initialization exception,Block application", e);
            }
            SceneProcessTemplate extendPointDefine = targetClass.getAnnotation(SceneProcessTemplate.class);
            // 是否懒加载场景流程配置
            boolean isLazyLoading = extendPointDefine.lazyLoading();
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                SceneProcess sceneProcess = method.getAnnotation(SceneProcess.class);
                if (Objects.isNull(sceneProcess)) {
                    continue;
                }
                try {
                    SceneConfiguration<?, ?> sceneConfiguration = (SceneConfiguration<?, ?>) method.invoke(bean);
                    sceneConfiguration.setScene(sceneProcess.scene());
                    sceneConfiguration.setDesc(sceneProcess.desc());
                    // 判断是否有手动设置懒加载
                    if (Objects.isNull(sceneConfiguration.getLazyLoading())) {
                        // 判断场景配置注解是否为default，如果为false则使用配置类上的加载模式
                        if (sceneProcess.lazyLoading()) {
                            sceneConfiguration.setLazyLoading(true);
                        }else {
                            sceneConfiguration.setLazyLoading(isLazyLoading);
                        }
                    }
                    EngineProcessContext.addEngineProcessDefine(sceneConfiguration);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // 调用自定义场景流程配置方法异常，阻断应用启动
                    throw new RuntimeException("MLE - Calling scenario processing scheme invoke exception,Block application", e);
                }
            }
        }
    }
}
