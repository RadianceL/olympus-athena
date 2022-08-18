package com.olympus.engine.extension.callback;

/**
 * 扩展点执行回调 <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
@FunctionalInterface
public interface ExtendPointCallback<Ext, Result> extends ExtensionCallback<Ext>{

    /**
     * 扩展点调用 返回执行结果
     * @param ext       扩展点
     * @return          扩展点返回
     */
    Result apply(Ext ext);

    /**
     * 执行扩展点
     * @param ext       扩展点
     */
    @Override
    default void runForGetExtensionCode(Ext ext) {
        apply(ext);
    }
}
