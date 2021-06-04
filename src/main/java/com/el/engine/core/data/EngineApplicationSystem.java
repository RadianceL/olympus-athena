package com.el.engine.core.data;

import com.el.engine.extension.template.Template;
import com.el.engine.utils.UncheckCastUtil;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 引擎应用系统配置 <br/>
 * since 2021/5/18
 *
 * @author eddie.lys
 */
public class EngineApplicationSystem {

    /**
     * 应用启动路径
     */
    private static String basePackage;
    /**
     * 场景模版配置类
     */
    private static Class<Template>[] sceneExtensionTemplateClasses;

    private static Class<?>[] sceneExtensionProcessClasses;

    public static Class<?>[] getSceneExtensionProcessClasses() {
        return sceneExtensionProcessClasses;
    }

    public static void setSceneExtensionProcessClasses(Class<?>[] sceneExtensionProcessClasses) {
        EngineApplicationSystem.sceneExtensionProcessClasses = sceneExtensionProcessClasses;
    }

    public static void setApplicationBasePackage(String basePackageName) {
        basePackage = basePackageName;
    }

    public static String getApplicationBasePackage() {
        return basePackage;
    }

    public static Class<Template>[] getSceneExtensionTemplateClasses() {
        return sceneExtensionTemplateClasses;
    }

    @SneakyThrows
    public static void setSceneExtensionTemplateClasses(Set<String> extensionTemplateClasses) {
        List<Class<Template>> list = new ArrayList<>();
        for (String extensionTemplateClass : extensionTemplateClasses) {
            Class<Template> aClass = UncheckCastUtil.castUncheckedObject(Class.forName(extensionTemplateClass));
            list.add(aClass);
        }
        EngineApplicationSystem.sceneExtensionTemplateClasses = list.toArray(new Class[0]);
    }
}
