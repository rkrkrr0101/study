package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {
    @Test
    void noDecorator(){
        Component realComponent=new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }
    @Test
    void Decorator1(){
        Component realComponent=new RealComponent();
        Component messageDeco=new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDeco);
        client.execute();
    }
    @Test
    void Decorator2(){
        Component realComponent=new RealComponent();
        Component messageDeco=new MessageDecorator(realComponent);
        Component timeDeco=new TimeDecorator(messageDeco);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDeco);
        client.execute();
    }
}
