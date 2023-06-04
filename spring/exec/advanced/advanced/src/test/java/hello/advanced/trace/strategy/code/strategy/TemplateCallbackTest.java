package hello.advanced.trace.strategy.code.strategy;

import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {
    @Test
    void callbackV1(){
        TimeLogTemplate template=new TimeLogTemplate();
        template.execute(()->log.info("비즈니스로직1실행"));
        template.execute(()->log.info("비즈니스로직2실행"));
    }
}
