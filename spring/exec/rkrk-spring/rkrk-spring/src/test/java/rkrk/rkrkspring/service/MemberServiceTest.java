package rkrk.rkrkspring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rkrk.rkrkspring.domain.Member;
import rkrk.rkrkspring.repo.MemoryMemberRepo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepo memberRepository;

    @BeforeEach
    public void beforeEach() {
        //memberRepository = new MemoryMemberRepo();
        memberRepository = new MemoryMemberRepo();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach

    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello123");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("hello123");
        Member member2 = new Member();
        member2.setName("hello123");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        try {
//            memberService.join(member2);
//            fail();
//            //then
//
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}