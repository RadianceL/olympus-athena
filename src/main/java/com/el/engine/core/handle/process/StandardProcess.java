package com.el.engine.core.handle.process;

import com.el.engine.identity.scheme.BusinessScheme;

/**
 * 标准流程<br/>
 * since 2021/4/22
 *
 * @author eddie.lys
 */
public interface StandardProcess<Req, Resp> {

    /**
     * 扩展点任务处理
     * @param request       请求参数
     * @param response      处理后参数
     */
    void process(BusinessScheme businessScheme,  Req request, Resp response);
}
