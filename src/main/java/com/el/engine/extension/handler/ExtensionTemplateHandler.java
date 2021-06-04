package com.el.engine.extension.handler;

import com.el.engine.extension.template.ExtensionTemplate;
import org.springframework.context.ApplicationContext;

/**
 * 标准扩展点模版处理器 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class ExtensionTemplateHandler extends TemplateHandler {

    public ExtensionTemplateHandler(ApplicationContext applicationContext, ExtensionTemplate extensionTemplate) {
        super(applicationContext, extensionTemplate);
    }

}
