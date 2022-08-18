package com.el.test.config.process;

import com.el.test.config.process.data.Req;
import com.el.test.config.process.data.Resp;
import com.el.test.service.CustomConfigService;
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
public class InitProcurementProcess implements StandardProcess<Req, Resp> {

    private final CustomConfigService customConfigService;

    @Override
    public void process(BusinessScheme businessScheme, Req request, Resp response) {
        log.info("流程1 -> 获取自定义配置:" + customConfigService.getConfig());
    }
}
