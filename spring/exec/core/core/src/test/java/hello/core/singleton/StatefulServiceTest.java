package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);
        //이경우,만약 중간에 텀이 있을때 상태값이 꼬여서 잘못결제될수가있음
        //두 쓰레드간에 순서보장이 안되니까..
        bean1.order("userA",10000);
        bean2.order("userA",20000);
        
        int price=bean1.getPrice();
        System.out.println("price = " + price);

        org.assertj.core.api.Assertions.assertThat(bean1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}