package com.el.engine.core.handle.point;


import com.el.engine.extension.callback.ExtendPointCallback;
import com.el.engine.identity.scheme.BusinessScheme;
import com.el.engine.test.ExtensionImpl;

/**
 * <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public class ExtensionRunner<Ext, Result> {

    public Result run(BusinessScheme bizScheme, ExtendPointCallback<Ext, Result> callback) {
        return callback.apply(this.getRealization(bizScheme));
    }

    Ext getRealization(BusinessScheme bizScheme) {
        return (Ext) new ExtensionImpl();
    }
}
