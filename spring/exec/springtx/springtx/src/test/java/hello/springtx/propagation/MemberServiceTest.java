package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired LogRepository logRepository;

    @Test
    void outerTxOff_success(){
        String username="outerTxOff_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
    @Test
    void outerTxOff_logFail(){
        String username="로그예외";

        assertThatThrownBy(()->memberService.joinV1(username)).isInstanceOf(RuntimeException.class);


        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());

    }
    @Test
    void singleTx(){
        String username="outerTxOff_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
    @Test
    void outerTxOn_success(){
        String username="outerTxOn_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
    @Test
    void outerTxOn_fail(){
        String username="로그예외outerTxOn_fail";


        assertThatThrownBy(()->memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        //트랜잭션전파로 인한 전체트랜잭션롤백
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }


}