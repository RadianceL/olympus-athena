package com.el.engine.test;

import com.el.engine.extension.Extension;
import com.el.engine.extension.ExtensionExecutor;

/**
 * <br/>
 * since 2021/5/13
 *
 * @author eddie.lys
 */
public class Main {

    public static void main(String[] args) {
        ExtensionExecutor<Extension> executor = new ExtensionExecutor<>();
        String param = executor.execute((extension) -> extension.print("param"));
        System.out.println(param);
    }
}
