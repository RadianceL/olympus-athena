package com.olympus.engine.extension.handler;

import com.olympus.engine.extension.Extension;
import com.olympus.engine.extension.handler.invoker.DefaultExtensionHelper;
import com.olympus.engine.extension.template.Template;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            if (Objects.isNull(extensionClass)) {
                continue;
            }
            Extension extension = applicationContext.getBean(extensionClass);
            Class<?> defaultExtension = findDefaultExtension(extensionClass);
            if (Objects.isNull(defaultExtension)) {
                throw new RuntimeException("MLE - ext default implementation undefined error "  +  extension.getClass());
            }
            extensions.put(defaultExtension.getName(), extension);
            // 初始化扩展点的默认实现
            initDefaultExtension(extensionClass);
        }
    }

    private void initDefaultExtension(Class<? extends Extension> extensionClass) {
        Class<?> defaultExtension = findDefaultExtension(extensionClass);
        if (Objects.isNull(defaultExtension)) {
            throw new RuntimeException("MLE - Template extension include unrealized Extension class");
        }
        Object extension = DefaultExtensionHelper.defaultExtensionInvoker(defaultExtension);
        DefaultExtensionContext.addDefaultExtension(extensionClass.getName(), extension);
    }

    /**
     * 初始化该类的默认方法 不支持接口继承
     * witch means， one extension can only find one include default method interface
     * @param extensionClass        扩展点类
     */
    private Class<?> findDefaultExtension(Class<?> extensionClass) {
        if (Objects.isNull(extensionClass)) {
            return null;
        }
        Class<?>[] interfaces = extensionClass.getInterfaces();
        if (interfaces.length == 0) {
            Class<?> superclass = extensionClass.getSuperclass();
            if (superclass != null && superclass.equals(Object.class)) {
                throw new RuntimeException("MLE - Template extension include unrealized Extension class");
            }
            return findDefaultExtension(superclass);
        }
        for (Class<?> extInterface : interfaces) {
            if (Objects.nonNull(extInterface) && extInterface.getInterfaces()[0].equals(Extension.class)) {
                return extInterface;
            }
        }

        throw new RuntimeException("MLE - Template extension include unrealized Extension class");
    }

    public Extension getExtension(String extClassName){
        return extensions.get(extClassName);
    }

    public boolean containsExtension(String extClassName) {
        return extensions.containsKey(extClassName);
    }

    public Extension getDefaultExtension(String extClassName){
        return DefaultExtensionContext.getDefaultExtension(extClassName);
    }

}
