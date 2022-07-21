package com.olympus.engine.core.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.olympus.engine.core.handler.process.StandardProcess;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 场景配置 <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public class SceneConfiguration<Req, Resp> {
    /**
     * 场景名称 继承自注解无需设置
     */
    private String scene;
    /**
     * 是否懒加载
     * 继承场景配置注解，流程定义注解中lazyLoading配置
     * 直接set > 场景注解 > 流程定义类注解
     */
    private Boolean isLazyLoading;
    /**
     * 描述 继承自注解无需设置
     */
    private String desc;
    /**
     * 标记map
     */
    private Map<String, String> tagMap;
    /**
     * 场景处理清单
     */
    private final List<String> sceneProcessList = new ArrayList<>();

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getLazyLoading() {
        return isLazyLoading;
    }

    public boolean getLazyLoadingIfPresent() {
        if (Objects.isNull(isLazyLoading)) {
            return false;
        }
        return isLazyLoading;
    }

    public void setLazyLoading(Boolean lazyLoading) {
        isLazyLoading = lazyLoading;
    }

    public Map<String, String> getTag(String tagKey) {
        return tagMap;
    }

    public void setTagMap(String tag) {
        if (StringUtils.isBlank(tag)) {
            return;
        }
        JSONObject tagJsonObj = JSON.parseObject(tag);
        Map<String, String> tagMap = new HashMap<>(8);
        for (String tagKey : tagJsonObj.keySet()) {
            tagMap.put(tagKey, tagJsonObj.getString(tagKey));
        }
        this.tagMap = tagMap;
    }

    public void addSceneProcess(Class<? extends StandardProcess<Req, Resp>> sceneClass) {
        this.sceneProcessList.add(sceneClass.getName());
    }

    public List<String> getSceneProcessList() {
        return sceneProcessList;
    }

    @Override
    public String toString() {
        return "SceneConfiguration{" +
                "name='" + scene + '\'' +
                ", desc='" + desc + '\'' +
                ", sceneProcessList=" + sceneProcessList +
                '}';
    }
}
