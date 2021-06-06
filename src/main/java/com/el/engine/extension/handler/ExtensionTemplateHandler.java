package com.el.engine.extension.handler;

import com.el.engine.extension.template.ExtensionTemplate;
import com.el.engine.identity.scheme.BusinessScheme;
import org.springframework.context.ApplicationContext;

/**
 * 标准扩展点模版处理器 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class ExtensionTemplateHandler extends TemplateHandler implements ExtensionTemplate {
    /**
     * 扩展点模版
     */
    private final ExtensionTemplate extensionTemplate;

    /**
     * 初始化标注扩展点模版处理器
     */
    public ExtensionTemplateHandler(ApplicationContext applicationContext, ExtensionTemplate extensionTemplate) {
        super(applicationContext, extensionTemplate);
        this.extensionTemplate = extensionTemplate;
    }


    @Override
    public boolean adapterTemplate(BusinessScheme businessScheme) {
        return extensionTemplate.adapterTemplate(businessScheme);
    }

    @Override
    public String ofBizChannel() {
        return extensionTemplate.ofBizChannel();
    }
}
