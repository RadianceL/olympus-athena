package com.el.test.config.process;

import com.el.test.config.process.data.Req;
import com.el.test.config.process.data.Resp;
import com.el.test.ext.TestExtension;
import com.olympus.engine.core.handler.point.ExtensionExecutor;
import com.olympus.engine.core.handler.process.StandardProcess;
import com.olympus.engine.identity.scheme.BusinessScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <br/>
 * since 2021/5/30
 *
 * @author eddie.lys
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequirementProcess implements StandardProcess<Req, Resp> {

    /**
     * 扩展点执行器
     */
    private final ExtensionExecutor<TestExtension> executor = new ExtensionExecutor<>(TestExtension.class);

    @Override
    public void process(BusinessScheme businessScheme, Req request, Resp response) {
        log.info("流程2 -> 需求部门决策流程 ---------------------- ");

        String result = executor.execute(businessScheme, extension -> extension.policy("正常流程，扩展点的业务实现"));
        log.info("流程2 -> 开始决策： 场景为{}, 执行结果为: {} ", businessScheme.getScene(), result);

        log.info("流程2 -> 需求部门决策流程 ---------------------- ");
    }
}
