package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("타임프록시 실행");
        long startTime = System.currentTimeMillis();
        Object invoke = methodProxy.invoke(target, args);
        long endTime = System.currentTimeMillis();
        long resTime=endTime-startTime;
        log.info("resTime={}",resTime);
        return invoke;
    }
}