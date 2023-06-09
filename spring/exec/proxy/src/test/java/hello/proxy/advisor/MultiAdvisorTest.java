package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
@Slf4j
public class MultiAdvisorTest {

    @Test
    void multiAdvisorTest1(){

        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory1=new ProxyFactory(target);

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface)proxyFactory1.getProxy();

        ProxyFactory proxyFactory2=new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory2.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface)proxyFactory2.getProxy();


        proxy2.save();
        proxy2.find();
    }
    @Test
    void multiAdvisorTest2(){

        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory1=new ProxyFactory(target);

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory1.addAdvisors(advisor1,advisor2);
        ServiceInterface proxy1 = (ServiceInterface)proxyFactory1.getProxy();




        proxy1.save();
        proxy1.find();
    }
    static class Advice1 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1호출");
            return invocation.proceed();
        }
    }
    static class Advice2 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2호출");
            return invocation.proceed();
        }
    }
}
