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
     * 业务标识
     */
    private final String biz;
    /**
     * 标签映射
     */
    private final Map<String, String> TAG_MAP = new HashMap<>(8);

    public BusinessScheme(String biz) {
        this.biz = biz;
    }

    public String getBiz() {
        return biz;
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

    public static BusinessScheme of(String biz) {
        return new BusinessScheme(biz);
    }
}
