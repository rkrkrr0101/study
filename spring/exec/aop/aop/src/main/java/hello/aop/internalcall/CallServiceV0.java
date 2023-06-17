package hello.aop.internalcall;

import hello.aop.exam.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {
    @Trace
    public void external(){
        log.info("call external");
        internal();
    }
    @Trace
    public void internal(){
        log.info("call internal");
    }
}
