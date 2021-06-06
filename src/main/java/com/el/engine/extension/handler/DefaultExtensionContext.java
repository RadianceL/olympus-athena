package com.el.engine.extension.handler;

import com.el.engine.extension.Extension;
import com.el.engine.utils.UncheckCastUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 默认扩展点缓存 <br/>
 * since 2021/6/6
 *
 * @author eddie.lys
 */
public class DefaultExtensionContext {

    /**
     * 默认扩展点缓存容器
     */
    private static final Map<String, Extension> DEFAULT_EXTENSIONS = new HashMap<>(64);

    public static void addDefaultExtension(String extClassName, Object extensionClass) {
        if (Objects.nonNull(getDefaultExtension(extClassName))) {
            throw new RuntimeException("MLE - this ext default interface already registered");
        }
        DEFAULT_EXTENSIONS.put(extClassName, UncheckCastUtil.castUncheckedObject(extensionClass));
    }

    public static Extension getDefaultExtension(String extClassName) {
        return DEFAULT_EXTENSIONS.get(extClassName);
    }
}
