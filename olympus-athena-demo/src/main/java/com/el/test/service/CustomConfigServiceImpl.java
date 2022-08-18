package com.el.test.service;

import org.springframework.stereotype.Service;

/**
 * @author eddie.lys
 * @since 2021/6/11
 */
@Service
public class CustomConfigServiceImpl implements CustomConfigService{

    @Override
    public String getConfig() {
        return "《用户自定义配置》";
    }
}
