package com.olympus.engine.extension.handler;

import com.olympus.engine.extension.template.NestedTemplate;
import org.springframework.context.ApplicationContext;

/**
 * 关联扩展点模版处理器 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class NestedTemplateHandler extends TemplateHandler {

    public NestedTemplateHandler(ApplicationContext applicationContext, NestedTemplate extensionTemplate) {
        super(applicationContext, extensionTemplate);
    }

}
