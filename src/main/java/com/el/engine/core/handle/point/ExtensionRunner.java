package com.el.engine.core.handle.point;

import com.el.engine.core.support.EngineExtensionContext;
import com.el.engine.extension.callback.ExtendPointCallback;
import com.el.engine.identity.scheme.BusinessScheme;
import com.el.engine.utils.UncheckCastUtil;

/**
 * 扩展点执行器<br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public class ExtensionRunner<Ext, Result> {

    public Result run(BusinessScheme bizScheme, String extClassName, ExtendPointCallback<Ext, Result> callback) {
        return callback.apply(this.getRealization(bizScheme, extClassName));
    }

    Ext getRealization(BusinessScheme bizScheme, String extClassName) {
        return UncheckCastUtil.castUncheckedObject(EngineExtensionContext.getExtension(bizScheme, extClassName));
    }
}
