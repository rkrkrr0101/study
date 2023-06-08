package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0(){
        Hello target=new Hello();
        log.info("start");
        String res1 = target.callA();
        log.info("result={}",res1);

        log.info("start");
        String res2 = target.callB();
        log.info("result={}",res2);
    }

    @Test
    void reflection1() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target=new Hello();
        Method methodCallA = classHello.getMethod("callA");
        Object res1 = methodCallA.invoke(target);
        log.info("res1={}",res1);
        Method methodCallB = classHello.getMethod("callB");
        Object res2 = methodCallB.invoke(target);
        log.info("res1={}",res2);
    }
    @Test
    void reflection2() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target=new Hello();
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA,target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB,target);
    }
    private void dynamicCall(Method method,Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object res1 = method.invoke(target);
        log.info("result={}",res1);

    }

    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }
        public String callB(){
            log.info("callB");
            return "B";
        }
    }

}
