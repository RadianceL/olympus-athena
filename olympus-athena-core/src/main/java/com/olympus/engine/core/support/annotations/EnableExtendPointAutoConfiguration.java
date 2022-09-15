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
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LogicEnginePostProcess.class)
public @interface EnableExtendPointAutoConfiguration {

    /**
     * 扩展点配置对象
     * @return      配置清单
     */
    Class<?>[] value();
}
