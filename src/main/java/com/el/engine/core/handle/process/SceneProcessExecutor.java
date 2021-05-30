package com.el.engine.core.handle.process;

import com.el.engine.core.support.EngineProcessContext;
import com.el.engine.identity.scheme.BusinessScheme;

import java.util.List;

/**
 * 场景流程引擎 <br/>
 * since 2021/4/22
 *
 * @author eddie.lys
 */
public class SceneProcessExecutor implements ProcessEngine {

    @Override
    public void start(BusinessScheme businessScheme, Object req, Object resp) {
        List<StandardProcess<Object, Object>> engineProcessDefine =
                EngineProcessContext.getEngineProcessDefine(businessScheme.getScene());
        for (StandardProcess<Object, Object> standardProcess : engineProcessDefine) {
            standardProcess.process(req, resp);
        }
    }
}

