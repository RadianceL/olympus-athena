package com.el.engine.core.handle.point;

import com.el.engine.extension.callback.ExtendPointCallback;
import com.el.engine.identity.scheme.BusinessScheme;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 扩展点执行器 <br/>
 * since 2021/5/13
 *
 * @author eddie.lys
 */
public class ExtensionExecutor<Ext>  {

    private final String extClassName;

    public ExtensionExecutor(Class<?> extClass){
        this.extClassName = extClass.getName();
    }

    public <Result> Result execute(BusinessScheme bizScheme, ExtendPointCallback<Ext, Result> callback) {
        ExtensionRunner<Ext, Result> extensionRunner = new ExtensionRunner<>();
        return extensionRunner.run(bizScheme, extClassName, callback);
    }
}
