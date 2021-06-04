package com.el.engine.core.support;

import com.el.engine.extension.Extension;
import com.el.engine.extension.handler.ExtensionTemplateHandler;
import com.el.engine.extension.handler.NestedTemplateHandler;
import com.el.engine.extension.handler.TemplateHandler;
import com.el.engine.extension.template.ExtensionTemplate;
import com.el.engine.extension.template.NestedTemplate;
import com.el.engine.extension.template.Template;
import com.el.engine.identity.scheme.BusinessScheme;
import com.el.engine.utils.UncheckCastUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 引擎流程上下文 <br/>
 * since 2021/5/30
 *
 * @author eddie.lys
 */
@Slf4j
public class EngineExtensionContext {
    /**
     * 扩展点模版缓存<业务渠道，模板定义>
     */
    private static final Map<String, Template> ENGINE_TEMPLATE_MAP = new ConcurrentHashMap<>(32);
    /**
     * 扩展点实例缓存<业务渠道，扩展点定义列表>
     */
    private static final Map<String, TemplateHandler> ENGINE_EXTENSION_RUNNER_MAP = new ConcurrentHashMap<>(32);
    /**
     * springBean容器
     */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        EngineExtensionContext.applicationContext = applicationContext;
    }

    public static void addExtensionTemplate(Template template) {
        if (Objects.isNull(applicationContext)) {
            throw new RuntimeException("MLE - application context is null error");
        }
        if (Objects.nonNull(ENGINE_TEMPLATE_MAP.get(template.ofBizChannel()))) {
            throw new RuntimeException("MLE - add template " + template.ofBizChannel() + " error, template definition conflict");
        }
        // 放入一级缓存
        ENGINE_TEMPLATE_MAP.put(template.ofBizChannel(), template);
        // 初始化模版定义
        initEngineExtensionRunner(template);
    }

    public static Extension getExtension(BusinessScheme businessScheme, String extClassName) {
        TemplateHandler templateHandler = ENGINE_EXTENSION_RUNNER_MAP.get(businessScheme.getBiz());
        return templateHandler.getExtension(extClassName);
    }

    private static void initEngineExtensionRunner(Template template) {
        if (template instanceof ExtensionTemplate) {
            // 标准扩展点模版
            ExtensionTemplate extensionTemplate = UncheckCastUtil.castUncheckedObject(template);
            if (Objects.isNull(extensionTemplate)) {
                throw new RuntimeException("MLE - init Template has null, please check template config");
            }
            ExtensionTemplateHandler extensionTemplateHandler =
                    new ExtensionTemplateHandler(applicationContext, extensionTemplate);
            String bizChannel = extensionTemplate.ofBizChannel();
            ENGINE_EXTENSION_RUNNER_MAP.put(bizChannel, extensionTemplateHandler);
        }else if (template instanceof NestedTemplate) {
            // 嵌套扩展点模版
            NestedTemplate extensionTemplate = UncheckCastUtil.castUncheckedObject(template);
            if (Objects.isNull(extensionTemplate)) {
                throw new RuntimeException("MLE - init Template has null, please check template config");
            }
            NestedTemplateHandler extensionTemplateHandler =
                    new NestedTemplateHandler(applicationContext, extensionTemplate);
            String bizChannel = extensionTemplate.ofBizChannel();
            ENGINE_EXTENSION_RUNNER_MAP.put(bizChannel, extensionTemplateHandler);
        }else {
            // 模版
            if (Objects.isNull(template)) {
                throw new RuntimeException("MLE - init Template has null, please check template config");
            }
            TemplateHandler extensionTemplateHandler =
                    new TemplateHandler(applicationContext, template);
            String bizChannel = template.ofBizChannel();
            ENGINE_EXTENSION_RUNNER_MAP.put(bizChannel, extensionTemplateHandler);
        }
    }


}
