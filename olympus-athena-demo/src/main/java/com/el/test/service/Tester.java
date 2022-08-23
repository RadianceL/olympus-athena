package com.el.test.service;

import com.olympus.engine.core.handler.exector.ProcessEngine;
import com.olympus.engine.core.handler.exector.SceneProcessExecutor;
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
@Order(Integer.MIN_VALUE)
public class Tester implements ApplicationListener<ApplicationReadyEvent> {

    private final ProcessEngine processEngine  = SceneProcessExecutor.getSceneProcessExecutorInstance();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//        BusinessScheme a = BusinessScheme.of("全链路流转流程", "现场采购发起");
//        Req req = new Req();
//        Resp resp = new Resp();
//        processEngine.start(a, req, resp);
//        System.out.println(resp.getResult());
//
//
//        BusinessScheme b = BusinessScheme.of("全链路流转流程", "P担");
//        processEngine.start(b, req, resp);
//        System.out.println(resp.getResult());
    }
}
