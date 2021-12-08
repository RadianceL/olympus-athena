package com.el.engine.core.handle.process;

import com.el.engine.core.support.EngineProcessContext;
import com.el.engine.identity.scheme.BusinessScheme;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 场景流程引擎 <br/>
 * since 2021/4/22
 *
 * @author eddie.lys
 */
public class SceneProcessExecutor implements ProcessEngine {

    @Override
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.SUPPORTS)
    public void start(BusinessScheme businessScheme, Object req, Object resp) {
        if (Objects.isNull(businessScheme)) {
            throw new RuntimeException("MLE - business scheme can not be null");
        }
        List<StandardProcess<Object, Object>> engineProcessDefine =
                EngineProcessContext.getEngineProcessDefine(businessScheme.getScene());
        for (StandardProcess<Object, Object> standardProcess : engineProcessDefine) {
            standardProcess.process(businessScheme, req, resp);
            if (SceneProcessBreaker.getBreaker()) {
                break;
            }
        }
    }
}

