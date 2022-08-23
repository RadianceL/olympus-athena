package com.olympus.engine.core.support;

import com.olympus.engine.extension.Extension;
import com.olympus.engine.extension.handler.DefaultExtensionContext;
import com.olympus.engine.extension.handler.ExtensionTemplateHandler;
import com.olympus.engine.extension.handler.NestedTemplateHandler;
import com.olympus.engine.extension.handler.TemplateHandler;
import com.olympus.engine.extension.template.ExtensionTemplate;
import com.olympus.engine.extension.template.NestedTemplate;
import com.olympus.engine.extension.template.Template;
import com.olympus.engine.identity.scheme.BusinessScheme;
import com.olympus.engine.utils.UncheckCastUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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
    private static final Map<String, List<Template>> ENGINE_TEMPLATE_MAP = new ConcurrentHashMap<>(32);
    /**
     * 扩展点实例缓存<业务渠道，扩展点定义列表>
     */
    private static final Map<String, List<TemplateHandler>> ENGINE_EXTENSION_RUNNER_MAP = new ConcurrentHashMap<>(32);
    /**
     * springBean容器
     */
    private static ApplicationContext applicationContext;

    /**
     * 当项目启动时保证该方法被调用且 applicationContext 不为空
     * @param applicationContext                    spring上下文
     */
    public static void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        EngineExtensionContext.applicationContext = applicationContext;
    }

    /**
     * 加载模版配置
     * @param template      模版对象
     */
    public static void addExtensionTemplate(Template template) {
        if (Objects.isNull(applicationContext)) {
            throw new RuntimeException("MLE - application context is null error");
        }
        for (String bizChannel : template.ofBizChannels()) {
            List<Template> templates = ENGINE_TEMPLATE_MAP.get(bizChannel);
            if (!CollectionUtils.isEmpty(templates) && templates.contains(template)) {
                throw new RuntimeException("MLE - add template " + template.getClass() + " error, template definition conflict");
            } else {
                templates = new ArrayList<>();
                templates.add(template);
                ENGINE_TEMPLATE_MAP.put(bizChannel, templates);
            }
        }
        // 初始化模版定义
        initEngineExtensionRunner(template);
    }

    public static Extension getExtension(BusinessScheme businessScheme, String extClassName) {
        List<TemplateHandler> templateHandlerList = ENGINE_EXTENSION_RUNNER_MAP.get(businessScheme.getBiz());
        if (CollectionUtils.isEmpty(templateHandlerList)) {
            return DefaultExtensionContext.getDefaultExtension(extClassName);
        }
        for (TemplateHandler templateHandler : templateHandlerList) {
            if (!templateHandler.containsExtension(extClassName)) {
                continue;
            }
            if (templateHandler instanceof ExtensionTemplateHandler) {
                ExtensionTemplateHandler extensionTemplateHandler = (ExtensionTemplateHandler) templateHandler;
                if (extensionTemplateHandler.adapterTemplate(businessScheme)) {
                    return templateHandler.getExtension(extClassName);
                }else {
                    return DefaultExtensionContext.getDefaultExtension(extClassName);
                }
            }
            return templateHandler.getExtension(extClassName);
        }
        throw new RuntimeException("MLE - can not find extension is runner map");
    }

    private static void initEngineExtensionRunner(Template template) {
        TemplateHandler templateHandler = null;
        if (template instanceof ExtensionTemplate) {
            // 标准扩展点模版
            ExtensionTemplate extensionTemplate = UncheckCastUtil.castUncheckedObject(template);
            if (Objects.isNull(extensionTemplate)) {
                throw new RuntimeException("MLE - init Template has null, please check template config");
            }
            templateHandler =
                    new ExtensionTemplateHandler(applicationContext, extensionTemplate);
        }else if (template instanceof NestedTemplate) {
            // 嵌套扩展点模版
            NestedTemplate extensionTemplate = UncheckCastUtil.castUncheckedObject(template);
            if (Objects.isNull(extensionTemplate)) {
                throw new RuntimeException("MLE - init Template has null, please check template config");
            }
            templateHandler =
                    new NestedTemplateHandler(applicationContext, extensionTemplate);
        }else {
            // 模版
            if (Objects.isNull(template)) {
                throw new RuntimeException("MLE - init Template has null, please check template config");
            }
            templateHandler =
                    new TemplateHandler(applicationContext, template);
        }
        String[] bizChannels = template.ofBizChannels();
        for (String bizChannel : bizChannels) {
            if (ENGINE_EXTENSION_RUNNER_MAP.containsKey(bizChannel)) {
                ENGINE_EXTENSION_RUNNER_MAP.get(bizChannel).add(templateHandler);
            }else {
                List<TemplateHandler> templateHandlerList = new ArrayList<>();
                templateHandlerList.add(templateHandler);
                ENGINE_EXTENSION_RUNNER_MAP.put(bizChannel, templateHandlerList);
            }
        }
    }

    public static void refreshNestedTemplateContext() {

    }

}
