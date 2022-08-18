package com.olympus.engine.core.support.annotations;

import com.olympus.engine.core.support.LogicEnginePostProcess;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 扩展点配置类<br/>
 * since 2021/4/27
 *
 * @author eddie.lys
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import(LogicEnginePostProcess.class)
public @interface EnableExtendPointAutoConfiguration {

    Class<?>[] value();
}
