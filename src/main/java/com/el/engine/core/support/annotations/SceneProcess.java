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
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SceneProcess {

    String value();

    int order() default 0;
}
