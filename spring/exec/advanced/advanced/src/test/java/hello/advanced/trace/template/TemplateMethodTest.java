package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }
    @Test
    void templateMethodV1(){
        AbstractTemplate template1=new SubClassLogic1();
        AbstractTemplate template2=new SubClassLogic2();
        template1.execute();
        template2.execute();
    }

    @Test
    void templateMethodV2(){
        AbstractTemplate template1=new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("익명내부1번");
            }
        };
        AbstractTemplate template2=new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("익명내부2번");
            }
        };
        template1.execute();
        template2.execute();
    }

    private void logic1() {
        long startTime=System.currentTimeMillis();
        log.info("비즈로직1실행");
        log.info("비즈로직1종료");
        long endTime=System.currentTimeMillis();
        long resultTime=endTime-startTime;
        log.info("resultTime={}",resultTime);
    }
    private void logic2() {
        long startTime=System.currentTimeMillis();
        log.info("비즈로직2실행");
        log.info("비즈로직2종료");
        long endTime=System.currentTimeMillis();
        long resultTime=endTime-startTime;
        log.info("resultTime={}",resultTime);
    }

}
