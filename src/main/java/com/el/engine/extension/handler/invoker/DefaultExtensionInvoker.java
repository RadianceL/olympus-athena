package com.el.engine.extension.handler.invoker;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认方法代理工具 <br/>
 * since 2021/6/5
 *
 * @author eddie.lys
 */
public class DefaultExtensionInvoker implements InvocationHandler {
    /**
     * 方法映射缓存
     */
    private final Map<Method, MethodHandle> methodHandleMap = new ConcurrentHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isDefault()) {
            MethodHandle defaultMethodHandle = methodHandleMap.computeIfAbsent(method, key -> {
                MethodHandle methodHandle = MethodHandlesUtil.getSpecialMethodHandle(method);
                return methodHandle.bindTo(proxy);
            });
            return defaultMethodHandle.invokeWithArguments(args);
        }
        return null;
    }

}
