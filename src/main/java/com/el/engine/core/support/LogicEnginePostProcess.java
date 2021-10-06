package com.el.engine.core.support;

import com.el.engine.core.config.EngineApplicationSystem;
import com.el.engine.core.support.annotations.EnableExtendPointAutoConfiguration;
import com.el.engine.core.support.annotations.Template;
import com.el.engine.extension.init.LogicEngineInitProcess;
import com.el.engine.utils.PackageScanUtils;
import com.el.engine.utils.UncheckCastUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 流程后置处理器 <br/>
 * since 2021/4/27
 *
 * @author eddie.lys
 */
@Slf4j
public class LogicEnginePostProcess implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        log.info("MLE - middle logic engine initialize start");
        // 初始化场景流程配置类
        MultiValueMap<String, Object> allAnnotationAttributes = annotationMetadata
                .getAllAnnotationAttributes(EnableExtendPointAutoConfiguration.class.getName());
        // 如果场景配置不存在，则忽略扩展点配置 直接返回
        if (Objects.isNull(allAnnotationAttributes)) {
            log.info("logic engine can`t find any process configuration");
            return new String[]{};
        }
        List<Object> values = allAnnotationAttributes.get("value");
        if (CollectionUtils.isEmpty(values)) {
            log.info("logic engine can`t find any process configuration");
            return new String[]{};
        }
        Class<com.el.engine.extension.template.Template>[] sceneProcessTemplateClasses = UncheckCastUtil.castUncheckedObject(values.get(0));
        if (Objects.isNull(sceneProcessTemplateClasses) || sceneProcessTemplateClasses.length == 0) {
            log.info("logic engine can`t find any process configuration");
            return new String[]{};
        }
        EngineApplicationSystem.setSceneExtensionProcessClasses(sceneProcessTemplateClasses);

        // 初始化场景定义 -> 获取项目启动路径所在包
        String className = annotationMetadata.getClassName();
        String applicationBasePackage = className.substring(0, className.lastIndexOf("."));
        EngineApplicationSystem.setApplicationBasePackage(applicationBasePackage);
        // 获取场景定义配置类
        log.info("MLE - scan application ExtendPointDefine class");
        Set<String> packageClass = PackageScanUtils.findPackageClass(applicationBasePackage, Template.class);
        EngineApplicationSystem.setSceneExtensionTemplateClasses(packageClass);
        // 场景初始化解析流程
        packageClass.add(LogicEngineInitProcess.class.getName());
        return packageClass.toArray(new String[]{});
    }
}
