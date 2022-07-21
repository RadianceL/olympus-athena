package com.olympus.engine.core.handler.point;

import com.olympus.engine.core.support.EngineExtensionContext;
import com.olympus.engine.extension.Extension;
import com.olympus.engine.extension.callback.ExtendPointCallback;
import com.olympus.engine.identity.scheme.BusinessScheme;
import com.olympus.engine.utils.UncheckCastUtil;

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
        Extension extension = EngineExtensionContext.getExtension(bizScheme, extClassName);
        return UncheckCastUtil.castUncheckedObject(extension);
    }
}
