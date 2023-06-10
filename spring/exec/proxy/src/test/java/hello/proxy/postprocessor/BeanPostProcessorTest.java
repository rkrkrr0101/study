package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
public class BeanPostProcessorTest {
    @Test
    void basicConfig(){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        Assertions.assertThatThrownBy(()->applicationContext.getBean(A.class)).isInstanceOf(NoSuchBeanDefinitionException.class);

    }
    @Configuration
    static class BeanPostProcessorConfig{
        @Bean(name="beanA")
        public A a(){
            return new A();
        }
        @Bean
        public AToBPostProcessor helloPostProcessor(){
            return new AToBPostProcessor();
        }
    }
    static class A{
        public void helloA(){
            log.info("A");
        }
    }
    static class B{
        public void helloB(){
            log.info("B");
        }
    }
    static class AToBPostProcessor implements BeanPostProcessor{

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}",beanName,bean);
            if (bean instanceof A){
                return new B();
            }
            return bean;
        }
    }
}
