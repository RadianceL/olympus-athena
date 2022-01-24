package com.el.engine.core.handler.process;

import java.util.Objects;

/**
 * 流程断路器
 *
 * @author eddie.lys
 * @since 12/8/2021
 */
public class SceneProcessBreaker {

    private static final ThreadLocal<Boolean> PROCESS_BREAKER = ThreadLocal.withInitial(() -> Boolean.FALSE);

    public static void intercept() {
        PROCESS_BREAKER.set(Boolean.TRUE);
    }

    public static boolean getBreaker() {
        if (Objects.isNull(PROCESS_BREAKER.get())) {
            return Boolean.FALSE;
        }
        return PROCESS_BREAKER.get();
    }

}
