package hello.springtx.propargation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config{
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource){
            return new DataSourceTransactionManager(dataSource);
        }
    }
    @Test
    void commit(){
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션커밋 시작");
        txManager.commit(status);
        log.info("트랜잭션커밋 완료");

    }
    @Test
    void rollback(){
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션롤백 시작");
        txManager.rollback(status);
        log.info("트랜잭션롤백 완료");

    }
    @Test
    void doubleCommit(){
        log.info("트랜잭션1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션커밋1 시작");
        txManager.commit(status1);


        log.info("트랜잭션2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션커밋2 시작");
        txManager.commit(status2);


    }
    @Test
    void doubleCommitRollback(){
        log.info("트랜잭션1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션커밋1 시작");
        txManager.commit(status1);


        log.info("트랜잭션2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션커밋2 시작");
        txManager.rollback(status2);



    }
    @Test
    void inner_commit(){
        log.info("외부트랜잭션시작");
        TransactionStatus outer=txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer isnew()={}",outer.isNewTransaction());
        log.info("내부트랜잭션시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("inner isnew()={}",inner.isNewTransaction());
        log.info("내부트랜잭션커밋");
        txManager.commit(inner);
        log.info("외부트랜잭션커밋");
        txManager.commit(outer);
    }
}
