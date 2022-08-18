package com.olympus.engine.extension.template;

import com.olympus.engine.extension.Extension;

/**
 * 模版定义 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public interface Template {

    /**
     * 获取业务渠道名称
     * @return          业务渠道名称
     */
    String[] ofBizChannels();
    /**
     * 对于当前嵌套模版适配的扩展点
     * @return           当前模版的扩展点列表
     */
    @SuppressWarnings ("unchecked")
    default Class<? extends Extension>[] ofExtension() {
        return new Class[0];
    }
}
