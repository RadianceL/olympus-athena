package com.el.engine.test;

import com.el.engine.extension.Extension;
import com.el.engine.core.handle.point.ExtensionExecutor;
import com.el.engine.identity.scheme.BusinessScheme;

/**
 * <br/>
 * since 2021/5/13
 *
 * @author eddie.lys
 */
public class Main {

    public static void main(String[] args) {
        BusinessScheme businessScheme = new BusinessScheme("123", "");
        ExtensionExecutor<TestExtension> executor = new ExtensionExecutor<>(TestExtension.class);
        String param = executor.execute(businessScheme, extension -> extension.print("para 12 m"));
        System.out.println(param);
    }
}
