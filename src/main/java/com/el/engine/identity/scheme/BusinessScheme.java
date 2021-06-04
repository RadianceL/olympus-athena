package com.el.engine.identity.scheme;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务方案 <br/>
 * since 2021/4/22
 *
 * @author eddie.lys
 */
public class BusinessScheme {
    /**
     * 场景
     */
    private final String scene;
    /**
     * 业务标识
     */
    private final String biz;
    /**
     * 标签映射
     */
    private final Map<String, String> TAG_MAP = new HashMap<>(8);

    public BusinessScheme(String scene, String biz) {
        this.biz = biz;
        this.scene = scene;
    }

    public String getBiz() {
        return biz;
    }

    public String getScene() {
        return scene;
    }

    /**
     * 添加标记
     * @param key           标记key
     * @param value         标记值
     */
    public void addTag(String key, String value) {
        TAG_MAP.put(key, value);
    }

    public String getTag(String key) {
        return TAG_MAP.get(key);
    }

    public Map<String, String> getTag() {
        return TAG_MAP;
    }

    public static BusinessScheme of(String scene, String biz) {
        return new BusinessScheme(scene, biz);
    }
}
