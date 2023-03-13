package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //g
        Member member = new Member();
        member.setName("rrr");


        //w
        Long savedId = memberService.join(member);

        //t
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }

    @Test
    public void 중복회원예외() throws Exception {
        //g
        Member member1 = new Member();
        member1.setName("rrr");
        Member member2 = new Member();
        member2.setName("rrr");
        //w
        memberService.join(member1);


        //t
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }

}