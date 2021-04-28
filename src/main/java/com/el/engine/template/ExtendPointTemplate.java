package com.el.engine.template;

import com.el.engine.handle.process.StandardProcess;

/**
 * 扩展点模版 <br/>
 * since 2021/4/27
 *
 * @author eddie.lys
 */
public interface ExtendPointTemplate<Req, Resp> {

    /**
     * 模版适配名
     * @return      模版名称
     */
    String ofName();

    /**
     * 适配的扩展点
     */


    /**
     * 适配器模版列表
     * @return      扩展点适配流程
     */
    Class<? extends StandardProcess<Req ,Resp>> adapterTemplate();

}
