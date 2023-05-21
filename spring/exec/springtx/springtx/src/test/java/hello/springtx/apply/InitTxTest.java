package hello.springtx.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
@Slf4j
public class InitTxTest {

    @Autowired Hello hello;

    @Test
    void abc(){

    }
    @TestConfiguration
    static class InitConfig{
        @Bean
        Hello hello(){
           return new Hello();
        }
    }

    static  class Hello{
        @PostConstruct
        @Transactional
        public void initV1(){
            log.info("initV1");
            printTxInfo();
        }
        @EventListener(value = ApplicationReadyEvent.class)
        @Transactional
        public void initV2(){
            log.info("initV2");
            printTxInfo();
        }
        private void printTxInfo(){
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}",txActive);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("tx readonly={}",readOnly);
        }
    }
}
