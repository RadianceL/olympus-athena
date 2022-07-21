package com.olympus.engine.utils;

import java.util.Objects;

/**
 * 未检查的强转工具 <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public class UncheckCastUtil {

    @SuppressWarnings("unchecked")
    public static <T> T castUncheckedObject(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        return (T) obj;
    }
}
