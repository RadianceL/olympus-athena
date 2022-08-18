package com.el.test.ext;


import com.olympus.engine.extension.Extension;

/**
 * <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
public interface TestExtension extends Extension {

    default String policy(String s) {
        return "this is default, input = {" + s + "}";
    }
}
