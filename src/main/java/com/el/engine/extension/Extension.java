package com.el.engine.extension;

import com.el.engine.identity.scheme.BusinessScheme;

/**
 * 扩展点基类 <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public interface Extension {

    /**
     * 判断当前扩展点是否为候选扩展点
     * @param businessScheme        业务方案
     * @return                      是否为候选扩展点
     */
    default boolean isCandidateExtension(BusinessScheme businessScheme) {
        return true;
    }
    /**
     * 扩展点执行排序
     * @return          排序 [Integer.MIN_VALUE， Integer.MAX_VALUE]
     */
    default int order() {
        return Integer.MIN_VALUE;
    }
}
