package com.el.engine.core;

import com.el.engine.identity.scheme.BusinessScheme;

/**
 * 流程处理器 <br/>
 * since 2021/4/27
 *
 * @author eddie.lys
 */
public interface ProcessEngine {

    void start(BusinessScheme businessScheme, Object req, Object resp);

}
