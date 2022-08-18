package com.el.test.config.process;

import com.el.test.config.process.data.Req;
import com.el.test.config.process.data.Resp;
import com.olympus.engine.core.handler.process.StandardProcess;
import com.olympus.engine.identity.scheme.BusinessScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <br/>
 * since 2021/5/30
 *
 * @author eddie.lys
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequirementDeterminationProcess implements StandardProcess<Req, Resp> {

    @Override
    public void process(BusinessScheme businessScheme, Req request, Resp response) {
        log.info("流程3 -> 执行需求部门确定流程");
    }
}
