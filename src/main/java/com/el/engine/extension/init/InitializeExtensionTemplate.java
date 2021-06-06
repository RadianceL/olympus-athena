package com.el.engine.extension.init;

import com.el.engine.core.data.EngineApplicationSystem;
import com.el.engine.core.support.EngineExtensionContext;
import com.el.engine.extension.template.Template;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 初始化扩展点 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class InitializeExtensionTemplate implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        if (applicationContext.getParent() == null) {
            EngineExtensionContext.setApplicationContext(applicationContext);
            Class<Template>[] sceneExtensionTemplateClasses = EngineApplicationSystem.getSceneExtensionTemplateClasses();
            for (Class<Template> sceneExtensionTemplateClass : sceneExtensionTemplateClasses) {
                try {
                    Template template = sceneExtensionTemplateClass.newInstance();
                    EngineExtensionContext.addExtensionTemplate(template);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("MTL - ext template new instance error", e);
                }
            }
            // 刷新内嵌模版上下文
            EngineExtensionContext.refreshNestedTemplateContext();
        }
    }
}
