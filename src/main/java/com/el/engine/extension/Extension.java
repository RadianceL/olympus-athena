package com.el.engine.extension;

/**
 * 扩展点基类 <br/>
 * since 2021/4/28
 *
 * @author eddie.lys
 */
public interface Extension {

    default String print(String s){
        return s;
    }
}
