package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(false)
class MemberJpaRepositoryTest {
    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember(){
        Member member=new Member("memberA");
        Member save = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(member.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }
    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        findMember1.setUsername("1111aaa");
        Member changeFindMember1 = memberJpaRepository.findById(member1.getId()).get();
        assertThat(changeFindMember1.getUsername()).isEqualTo("1111aaa");

        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        memberJpaRepository.delete(member1);
        assertThat(memberJpaRepository.count()).isEqualTo(1);



    }

    @Test
    public void findTest(){
        Member m1=new Member("AAA",10);
        Member m2=new Member("BBB",20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThen("BBB", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("BBB");
        assertThat(result.get(0).getAge()).isEqualTo(20);
    }
    @Test
    public void paging(){
        memberJpaRepository.save(new Member("member1",10));
        memberJpaRepository.save(new Member("member2",10));
        memberJpaRepository.save(new Member("member3",10));
        memberJpaRepository.save(new Member("member4",10));
        memberJpaRepository.save(new Member("member5",10));


        List<Member> byPage = memberJpaRepository.findByPage(10, 0, 3);
        for (Member member : byPage) {
            System.out.println("member = " + member);
        }
        long l = memberJpaRepository.totalCount(10);
        System.out.println("l = " + l);

        assertThat(byPage.size()).isEqualTo(3);
        assertThat(l).isEqualTo(5);
    }
}