package com.el.engine.core.support.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 流程场景模版 <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SceneProcessTemplate {

    /**
     * 描述 - 无实际意义
     * @return          描述
     */
    String value() default "";

    /**
     * 是否懒加载
     * @return          是否懒加载流程定义
     */
    boolean lazyLoading() default false;

}
