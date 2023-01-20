package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingleWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1=bean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2=bean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
        count2=bean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }
    @Scope("singleton")

    static class ClientBean{


        @Autowired private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean=prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count= prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;
        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("프로토타입init"+this);
        }
        @PreDestroy
        public void destory(){
            System.out.println("호출안됨 디스트로이");
        }

    }
}
