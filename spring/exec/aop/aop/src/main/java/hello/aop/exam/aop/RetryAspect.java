package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RetryAspect {
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry]{} retry={}",joinPoint.getSignature(),retry);
        int maxRetry = retry.value();
        Exception exceptionTemp=null;
        for (int i=0;i<maxRetry;i++ ){
            try {
                log.info("[retryCount]{}/ maxRetry={}",i,maxRetry);
                return joinPoint.proceed();
            }catch(Exception e){

                exceptionTemp=e;
            }

        }
        throw exceptionTemp;

    }
}
