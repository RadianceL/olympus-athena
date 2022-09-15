package com.olympus.engine.extension.handler;

import com.olympus.engine.extension.template.ExtensionTemplate;
import com.olympus.engine.extension.template.NestedTemplate;
import org.springframework.context.ApplicationContext;

/**
 * 关联扩展点模版处理器 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class NestedTemplateHandler extends TemplateHandler implements NestedTemplate {

    /**
     * 扩展点模版
     */
    private final NestedTemplate extensionTemplate;

    public NestedTemplateHandler(ApplicationContext applicationContext, NestedTemplate extensionTemplate) {
        super(applicationContext, extensionTemplate);
        this.extensionTemplate = extensionTemplate;
    }

    @Override
    public Class<? extends NestedTemplate> ifParentTemplateExist() {
        return extensionTemplate.ifParentTemplateExist();
    }

    @Override
    public Class<? extends ExtensionTemplate>[] nestedExtensionTemplate() {
        return extensionTemplate.nestedExtensionTemplate();
    }

    @Override
    public String[] ofBizChannels() {
        return extensionTemplate.ofBizChannels();
    }
}
