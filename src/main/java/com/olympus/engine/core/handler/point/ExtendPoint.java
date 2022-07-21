package com.olympus.engine.core.handler.point;

import com.olympus.engine.identity.scheme.BusinessScheme;

/**
 * 扩展点定义 <br/>
 * since 2021/4/22
 *
 * @author eddie.lys
 */
public interface ExtendPoint {

    /**
     * 获取业务定义
     * @return      业务定义
     */
    BusinessScheme verifyBusinessScheme();

}

