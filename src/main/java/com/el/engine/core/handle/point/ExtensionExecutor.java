package com.el.engine.core.handle.point;

import com.el.engine.extension.callback.ExtendPointCallback;
import com.el.engine.identity.scheme.BusinessScheme;
import org.springframework.core.ResolvableType;

/**
 * 扩展点执行器 <br/>
 * since 2021/5/13
 *
 * @author eddie.lys
 */
public class ExtensionExecutor<Ext>  {

    private Class<?> extClass;

    public ExtensionExecutor(Class<?> extClass){
        this.extClass = extClass;
    }

    public <Result> Result execute(BusinessScheme bizScheme, ExtendPointCallback<Ext, Result> callback) {
        ExtensionRunner<Ext, Result> extensionRunner = new ExtensionRunner<>();
        String extClassName = extClass.getName();
        return extensionRunner.run(bizScheme, extClassName, callback);
    }
}
