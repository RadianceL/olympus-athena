package com.olympus.engine.extension.template;

import com.olympus.engine.identity.scheme.BusinessScheme;

/**
 * 扩展点模版 <br/>
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

    /**
     * 获取该业务模版的上级关联模版
     * 注：该方法不建议使用，当业务系统足够大时会引起业务错乱
     * @return             嵌套模板
     */
    default Class<? extends NestedTemplate> ifParentTemplateExist() {
        // 当该方法返回null时，不具备向上查询能力，不为null时抛出警告
        return null;
    }
}
