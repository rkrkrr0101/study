package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("타임프록시 실행");
        long startTime = System.currentTimeMillis();
        //Object invoke = method.invoke(target, args);
        Object invoke = invocation.proceed();
        long endTime = System.currentTimeMillis();
        long resTime=endTime-startTime;
        log.info("resTime={}",resTime);
        return invoke;
    }
}
