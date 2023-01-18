package com.el.test;

import com.el.test.config.SceneProcessConfiguration;
import com.el.test.service.Tester;
import com.olympus.engine.core.support.annotations.EnableExtendPointAutoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试工程启动类<br/>
 * since 2021/4/26
 *
 * @author eddie.lys
 */
@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableExtendPointAutoConfiguration({SceneProcessConfiguration.class})
public class MiddleLogicEngineTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddleLogicEngineTestApplication.class, args);
        Tester.test();
    }
}
