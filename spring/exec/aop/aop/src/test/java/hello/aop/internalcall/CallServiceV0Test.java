package hello.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CallServiceV0Test {
    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external(){
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}