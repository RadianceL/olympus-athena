package com.el.engine.extension.handler.invoker;

import com.el.engine.utils.UncheckCastUtil;

import java.lang.reflect.Proxy;

/**
 * <br/>
 * since 2021/6/5
 *
 * @author eddie.lys
 */
public class DefaultExtensionHelper {

    public static <T> T defaultExtensionInvoker(Class<T> cls) {
        DefaultExtensionInvoker invocationHandler = new DefaultExtensionInvoker();
        return UncheckCastUtil.castUncheckedObject(Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[] { cls },
                invocationHandler));
    }
}
