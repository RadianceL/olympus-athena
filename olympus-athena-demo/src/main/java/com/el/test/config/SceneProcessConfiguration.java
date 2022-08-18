package com.el.test.config;

import com.el.test.config.process.InitProcurementProcess;
import com.el.test.config.process.RequirementDeterminationProcess;
import com.el.test.config.process.RequirementProcess;
import com.el.test.config.process.data.Req;
import com.el.test.config.process.data.Resp;
import com.el.test.config.process.ProcurementProcess;
import com.olympus.engine.core.config.SceneConfiguration;
import com.olympus.engine.core.support.annotations.SceneProcess;
import com.olympus.engine.core.support.annotations.SceneProcessTemplate;

/**
 * <br/>
 * since 2021/5/18
 *
 * @author eddie.lys
 */
@SceneProcessTemplate
public class SceneProcessConfiguration {

    @SceneProcess(scene = "全链路流转流程", lazyLoading = true)
    public SceneConfiguration<Req, Resp> test() {
        SceneConfiguration<Req, Resp> sceneConfiguration = new SceneConfiguration<>();
        sceneConfiguration.addSceneProcess(InitProcurementProcess.class);
        sceneConfiguration.addSceneProcess(RequirementProcess.class);
        sceneConfiguration.addSceneProcess(RequirementDeterminationProcess.class);
        sceneConfiguration.addSceneProcess(ProcurementProcess.class);
        return sceneConfiguration;
    }

}
