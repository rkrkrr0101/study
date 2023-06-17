package hello.aop.internalcall;

import hello.aop.exam.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {
    //private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    @Trace
    public void external(){
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceProvider.getObject(CallServiceV2.class);
        callServiceV2.internal();
    }
    @Trace
    public void internal(){
        log.info("call internal");
    }
}
