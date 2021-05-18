package com.el.engine.core.data;

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
    private static Class<?>[] sceneProcessTemplateClasses;


    public static void setApplicationBasePackage(String basePackageName) {
        basePackage = basePackageName;
    }

    public static String getApplicationBasePackage() {
        return basePackage;
    }

    public static Class<?>[] getSceneProcessTemplateClasses() {
        return sceneProcessTemplateClasses;
    }

    public static void setSceneProcessTemplateClasses(Class<?>[] sceneProcessTemplateClasses) {
        EngineApplicationSystem.sceneProcessTemplateClasses = sceneProcessTemplateClasses;
    }
}
