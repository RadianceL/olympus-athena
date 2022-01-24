package com.el.engine.extension.init;

import com.el.engine.core.config.EngineApplicationSystem;
import com.el.engine.core.support.EngineExtensionContext;
import com.el.engine.extension.template.Template;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 初始化扩展点 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class InitializeExtensionTemplate implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        EngineExtensionContext.setApplicationContext(applicationContext);
        Class<Template>[] sceneExtensionTemplateClasses = EngineApplicationSystem.getSceneExtensionTemplateClasses();
        if (Objects.isNull(sceneExtensionTemplateClasses) || sceneExtensionTemplateClasses.length == 0) {
            return;
        }
        for (Class<Template> sceneExtensionTemplateClass : sceneExtensionTemplateClasses) {
            try {
                Template template = sceneExtensionTemplateClass.getDeclaredConstructor().newInstance();
                EngineExtensionContext.addExtensionTemplate(template);
            } catch(InstantiationException | IllegalAccessException |
                    InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("MTL - ext template new instance error", e);
            }
        }
        // 刷新内嵌模版上下文
        EngineExtensionContext.refreshNestedTemplateContext();
    }
}
