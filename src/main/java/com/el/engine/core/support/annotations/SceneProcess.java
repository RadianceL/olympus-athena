package com.el.engine.core.support.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 场景定义 <br/>
 * since 2021/4/27
 *
 * @author eddie.lys
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SceneProcess {
    /**
     * 场景
     * @return  场景名称
     */
    String scene();
    /**
     * 场景描述
     * @return      场景描述
     */
    String desc() default "";
    /**
     * 场景标识
     * @return      场景标识 json格式
     */
    String tag() default "{}";
    /**
     * 是否懒加载
     * @return          是否懒加载流程定义
     */
    boolean lazyLoading() default false;
}
