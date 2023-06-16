package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ThisTargetTest.ThisTargetAspect.class)
public class ThisTargetTest {
    @Autowired
    MemberService memberService;

    @Test
    void success(){
        log.info("memberService proxy={}",memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect{
        @Around("this(hello.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }
        @Around("target(hello.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }
        @Around("this(hello.aop.member.MemberServiceImpl)")
        public Object doThisClass(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-class] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }
        @Around("target(hello.aop.member.MemberServiceImpl)")
        public Object doTargetClass(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-class] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
