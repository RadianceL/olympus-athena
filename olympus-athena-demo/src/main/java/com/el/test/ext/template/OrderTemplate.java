package com.el.test.ext.template;

import com.el.test.ext.OrderExtensionImpl;
import com.el.test.ext.TestExtension;
import com.olympus.engine.core.support.annotations.Template;
import com.olympus.engine.extension.Extension;
import com.olympus.engine.extension.template.ExtensionTemplate;
import com.olympus.engine.identity.scheme.BusinessScheme;

/**
 * <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
@Template
public class OrderTemplate implements ExtensionTemplate {

    @Override
    public boolean adapterTemplate(BusinessScheme businessScheme) {
        return true;
    }


    @Override
    public String[] ofBizChannels() {
        return new String[]{"P担"};
    }

    @Override
    public Class<? extends Extension>[] ofExtension() {
        return new Class[]{OrderExtensionImpl.class};
    }
}
