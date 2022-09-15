package com.olympus.engine.extension.template;

import com.olympus.engine.identity.scheme.BusinessScheme;

/**
 * 嵌套模版结构 <br/>
 * 未完成 无法使用
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public interface NestedTemplate extends Template {

    /**
     * 判定是否适配当前模板
     * @return            是否适配当前模板
     */
    default boolean adapterTemplate(BusinessScheme businessScheme) {
        return true;
    }

    /**
     * 获取该业务模版的上级关联模版 将当前扩展点配置填充到上级扩展点中 补充上级扩展点内容
     * PS：该方法不建议使用，当业务系统足够大时会引起业务错乱
     * 场景：当主业务系统核心代码不可变更，同时需要对核心业务扩充扩展点，则使用该方法
     * @return             嵌套模板
     */
    default Class<? extends NestedTemplate> ifParentTemplateExist() {
        // 当该方法返回null时，不具备向上查询能力，不为null时抛出警告
        return null;
    }

    /**
     * 获取关联的扩展点模版定义 如果一个biz关联到多个相同方法的扩展点，则依次执行
     * @return              关联的扩展点模版定义
     */
    default Class<? extends ExtensionTemplate>[] nestedExtensionTemplate() {
        return null;
    }
}
