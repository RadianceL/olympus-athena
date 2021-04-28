package com.el.engine.handle.process;

/**
 * 标准流程<br/>
 * since 2021/4/22
 *
 * @author eddie.lys
 */
public abstract class StandardProcess<Req, Resp> {

    /**
     * 扩展点任务处理
     * @param request       请求参数
     * @param response      处理后参数
     */
    public abstract void process(Req request, Resp response);
}
