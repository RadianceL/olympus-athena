package com.el.engine.core.data;

import com.el.engine.handle.process.StandardProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景配置 <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public class SceneConfiguration<Req, Resp> {
    /**
     * 场景名称
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 场景处理清单
     */
    private final List<String> sceneProcessList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", sceneProcessList=" + sceneProcessList +
                '}';
    }
}
