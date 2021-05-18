package com.el.engine.extension;

import com.el.engine.extension.callback.ExtendPointCallback;
import com.el.engine.identity.scheme.BusinessScheme;

/**
 * 扩展点执行器 <br/>
 * since 2021/5/13
 *
 * @author eddie.lys
 */
public class ExtensionExecutor<Ext>  {

    public <Result> Result execute(BusinessScheme bizScheme, ExtendPointCallback<Ext, Result> callback) {
        ExtensionRunner<Ext, Result> extensionRunner = new ExtensionRunner<>();
        return extensionRunner.run(bizScheme,callback);
    }
}
