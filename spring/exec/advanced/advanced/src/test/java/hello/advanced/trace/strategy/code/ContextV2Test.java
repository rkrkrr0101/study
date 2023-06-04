package hello.advanced.trace.strategy.code;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(()->log.info("abc"));
        context.execute(new StrategyLogic2());
    }
}
