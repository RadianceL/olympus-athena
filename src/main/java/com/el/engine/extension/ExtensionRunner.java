package com.el.engine.extension;


import com.el.engine.extension.callback.ExtendPointCallback;
import com.el.engine.test.ExtensionImpl;

/**
 * <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public class ExtensionRunner<Ext, Result> {

    public Result run(ExtendPointCallback<Ext, Result> callback) {
        return callback.apply(this.getRealization());
    }

    Ext getRealization() {
        return (Ext) new ExtensionImpl();
    }
}
