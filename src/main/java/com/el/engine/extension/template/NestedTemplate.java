package com.el.engine.extension.template;

/**
 * 嵌套模版结构 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public interface NestedTemplate extends Template{

    /**
     * 获取关联的扩展点模版定义
     * @return              关联的扩展点模版定义
     */
    Class<? extends ExtensionTemplate> nestedExtensionTemplate();
}
