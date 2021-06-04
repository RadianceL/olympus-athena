package com.el.engine.test;

/**
 * <br/>
 * since 2021/5/13
 *
 * @author eddie.lys
 */
public class TestExtensionImpl implements TestExtension {

    public TestExtensionImpl() {
    }

    @Override
    public String print(String s) {
        return s;
    }
}
