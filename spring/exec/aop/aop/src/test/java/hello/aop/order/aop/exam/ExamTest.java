package hello.aop.order.aop.exam;

import hello.aop.exam.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ExamTest {
    @Autowired
    ExamService examService;

    @Test
    void test(){
        for (int i=0;i<5;i++){
            log.info("{}",i);
            examService.request("data"+i);
        }
    }

}