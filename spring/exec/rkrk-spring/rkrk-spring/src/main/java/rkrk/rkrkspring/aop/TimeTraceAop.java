package rkrk.rkrkspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* rkrk.rkrkspring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START:" + joinPoint.toString());
        try {
            //대상메인메소드실행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timems = finish - start;
            System.out.println("END:" + joinPoint.toString() + " " + timems + "ms");
        }


    }
}
