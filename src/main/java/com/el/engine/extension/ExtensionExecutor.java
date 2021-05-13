package com.el.engine.extension;

import com.el.engine.extension.callback.ExtendPointCallback;

/**
 * 扩展点执行器 <br/>
 * since 2021/5/13
 *
 * @author eddie.lys
 */
public class ExtensionExecutor<Ext>  {

    public <Result> Result execute(ExtendPointCallback<Ext, Result> callback) {
        ExtensionRunner<Ext, Result> extensionRunner = new ExtensionRunner<>();
        return extensionRunner.run(callback);
    }
}
