package com.el.test.service;

import com.el.test.config.process.data.Req;
import com.el.test.config.process.data.Resp;
import com.olympus.engine.core.handler.exector.ProcessEngine;
import com.olympus.engine.core.handler.exector.SceneProcessExecutor;
import com.olympus.engine.identity.scheme.BusinessScheme;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * since 2021/5/30
 *
 * @author eddie.lys
 */
@Service
public class Tester {

    public static void test() {
        BusinessScheme a = BusinessScheme.of("全链路流转流程", "现场采购发起");
        Req req = new Req();
        Resp resp = new Resp();
        SceneProcessExecutor.getSceneProcessExecutorInstance().start(a, req, resp);
        System.out.println(resp.getResult());

        BusinessScheme b = BusinessScheme.of("全链路流转流程", "P担");
        SceneProcessExecutor.getSceneProcessExecutorInstance().start(b, req, resp);
        System.out.println(resp.getResult());
    }
}
