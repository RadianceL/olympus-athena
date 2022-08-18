package com.el.test.ext;

import org.springframework.stereotype.Service;

/**
 * <br/>
 * since 2021/6/4
 *
 * @author eddie.lys
 */
@Service
public class OrderExtensionImpl implements TestExtension {

    @Override
    public String policy(String s){
        return "这是现场下单执行逻辑";
    }
}
