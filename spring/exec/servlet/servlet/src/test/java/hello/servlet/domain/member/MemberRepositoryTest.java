package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //g
        Member member = new Member("hello", 20);
        //w
        Member saveMember = memberRepository.save(member);
        //t
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        //g
        Member member1 = new Member("hello1", 20);
        Member member2 = new Member("hello2", 30);
        Member saveMember1 = memberRepository.save(member1);
        Member saveMember2 = memberRepository.save(member2);
        //w
        List<Member> result = memberRepository.findAll();
        //t
        Assertions.assertThat(2).isEqualTo(result.size());
        Assertions.assertThat(result).contains(member1, member2);
    }

}