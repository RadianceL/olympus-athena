package com.el.engine.core.support;

import com.el.engine.core.data.SceneConfiguration;
import com.el.engine.core.support.annotations.EnableExtendPointAutoConfiguration;
import com.el.engine.core.support.annotations.SceneProcessTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * 流程后置处理器 <br/>
 * since 2021/4/27
 *
 * @author eddie.lys
 */
@Configuration
public class LogicEnginePostProcess implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        MultiValueMap<String, Object> allAnnotationAttributes = annotationMetadata
                .getAllAnnotationAttributes(EnableExtendPointAutoConfiguration.class.getName());
        if (Objects.isNull(allAnnotationAttributes)) {
            return new String[0];
        }
        List<Object> values = allAnnotationAttributes.get("value");
        if (CollectionUtils.isEmpty(values)) {
            return new String[0];
        }
        Class<?>[] classes = (Class<?>[]) values.get(0);
        for (Class<?> targetClass : classes) {
            Object bean;
            try {
                bean = targetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("调用场景处理方案配置初始化配置类异常");
            }
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                SceneProcessTemplate annotation = method.getAnnotation(SceneProcessTemplate.class);
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

        return new String[0];
    }
}
