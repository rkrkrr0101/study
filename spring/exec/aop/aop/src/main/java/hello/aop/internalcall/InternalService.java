package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import hello.aop.exam.annotation.Trace;
@Component
@Slf4j
public class InternalService {
    @Trace
    public void internal(){
        log.info("call internal");
    }
}
