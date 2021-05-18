package com.el.engine.core.support;

import com.el.engine.core.data.EngineApplicationSystem;
import com.el.engine.core.data.SceneConfiguration;
import com.el.engine.core.support.annotations.SceneProcess;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 逻辑引擎初始化控制 <br/>
 * since 2021/5/18
 *
 * @author eddie.lys
 */
public class LogicEngineInitProcess {

    @PostConstruct
    public void init() {
        Class<?>[] sceneProcessTemplateClasses = EngineApplicationSystem.getSceneProcessTemplateClasses();
        initSceneProcessTemplate(sceneProcessTemplateClasses);
    }

    private void initSceneProcessTemplate(Class<?>[] sceneProcessTemplateClasses) {
        for (Class<?> targetClass : sceneProcessTemplateClasses) {
            Object bean;
            try {
                bean = targetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("调用场景处理方案配置初始化配置类异常");
            }
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                SceneProcess annotation = method.getAnnotation(SceneProcess.class);
                if (Objects.isNull(annotation)) {
                    continue;
                }
                try {
                    SceneConfiguration<?, ?> sceneConfiguration = (SceneConfiguration<?, ?>) method.invoke(bean);
                    System.out.println(sceneConfiguration);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("调用场景处理方案配置初始化异常", e);
                }
            }
        }
    }

}
