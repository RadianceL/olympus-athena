package com.el.engine.extension.callback;

/**
 * 扩展执行回调 <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public interface ExtensionCallback<Ext> {

    /**
     * 执行扩展点
     * @param ext       扩展点
     */
    void runForGetExtensionCode(Ext ext);

}
