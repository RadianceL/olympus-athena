package com.el.engine.extension.handler;

import com.el.engine.extension.Extension;
import com.el.engine.extension.template.Template;
import org.springframework.context.ApplicationContext;

import java.util.*;

/**
 * <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class TemplateHandler {

    /**
     * 扩展点列表
     */
    private final Map<String, Extension> extensions = new HashMap<>(8);

    public TemplateHandler(ApplicationContext applicationContext, Template template) {
        Class<? extends Extension>[] classes = template.ofExtension();
        for (Class<? extends Extension> extensionClass : classes) {
            Extension extension = applicationContext.getBean(extensionClass);
            extensions.put(extensionClass.getName(), extension);
        }
    }

    public Extension getExtension(String extClassName){
        return extensions.get(extClassName);
    }

}
