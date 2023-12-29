package com.olympus.engine.extension.init;

import com.olympus.engine.core.config.EngineApplicationSystem;
import com.olympus.engine.core.support.EngineExtensionContext;
import com.olympus.engine.extension.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 初始化扩展点 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
@Slf4j
@AutoConfigureOrder(Integer.MIN_VALUE)
public class InitializeExtensionTemplate implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent contextRefreshedEvent) {
        log.info("MLE - middle logic engine initialize ext start");
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
