package com.olympus.engine.extension.template;

import com.olympus.engine.identity.scheme.BusinessScheme;

/**
 * 扩展点模版 <br/>
 * 当相同扩展点存在多个扩展点实现，只执行第一个
 * since 2021/4/27
 *
 * @author eddie.lys
 */
public interface ExtensionTemplate extends Template {

    /**
     * 判定是否适配当前模板
     * @return            是否适配当前模板
     */
    default boolean adapterTemplate(BusinessScheme businessScheme) {
        return true;
    }

}
