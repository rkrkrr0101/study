package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember() {
        Member member = new Member("MemberA");
        Member saveMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember).isEqualTo(member);

    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        findMember1.setUsername("1111aaa");
        Member changeFindMember1 = memberRepository.findById(member1.getId()).get();
        assertThat(changeFindMember1.getUsername()).isEqualTo("1111aaa");

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);
        assertThat(memberRepository.count()).isEqualTo(1);


    }

    @Test
    public void findTest() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        //List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("BBB", 15);
        List<Member> result = memberRepository.findUserAge("BBB", 20);

        assertThat(result.get(0).getUsername()).isEqualTo("BBB");
        assertThat(result.get(0).getAge()).isEqualTo(20);
    }

    @Test
    public void findUsernameTest() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        //List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("BBB", 15);
        //List<Member> result = memberRepository.findUserAge("BBB",20);
        List<String> result = memberRepository.findUsernameList();
        System.out.println("result = " + result.get(0));
        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    public void findDtoTest() {
        Team teamA = new Team("teamA");

        teamRepository.save(teamA);


        Member m1 = new Member("AAA", 10);
        m1.changeTeam(teamA);
        memberRepository.save(m1);

        List<MemberDto> result = memberRepository.findMemberDto();

        System.out.println("result = " + result.get(0));
        // assertThat(result.).isEqualTo(2);

    }

    @Test
    public void findByNameTest() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        //List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("BBB", 15);
        //List<Member> result = memberRepository.findUserAge("BBB",20);
        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        System.out.println("result = " + result.get(0));
        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    public void returnTypeTest() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> listByUsername = memberRepository.findListByUsername("AAAgg");
        System.out.println("listByUsername = " + listByUsername.size());
        Member member = memberRepository.findMemberByUsername("AAA");
        System.out.println("member = " + member);
        Optional<Member> OptionalMember = memberRepository.findMemberOptionalByUsername("AAA");

    }

    @Test
    public void paging() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> pageMember = memberRepository.findByAge(10, pageRequest);

        Page<MemberDto> memberDto = pageMember.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        List<Member> content = pageMember.getContent();
        long totalElements = pageMember.getTotalElements();
        System.out.println("totalElements = " + totalElements);
        int totalPages = pageMember.getTotalPages();
        System.out.println("totalPages = " + totalPages);
        int number = pageMember.getNumber();
        System.out.println("number = " + number);

        for (Member member : content) {
            System.out.println("member = " + member);
        }


        assertThat(content.size()).isEqualTo(3);

    }

    @Test
    public void bulkUpdate() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        int resultCount = memberRepository.bulkAgePlus(20);

        List<Member> byGreaterAge = memberRepository.findByAgeGreaterThan(20);
        for (Member member : byGreaterAge) {
            System.out.println("member = " + member);

        }
    }

    @Test
    public void findMemberLazy() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findMemberFetchJoin();
        for (Member member : members) {
            System.out.println("member = " + member.getTeam().getName());

        }
    }
    @Test
    public void queryHint(){
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        Member findMember = memberRepository.findReadOnlyByUsername(member1.getUsername());
        findMember.setUsername("member2");
        System.out.println("findMember.getUsername() = " + findMember.getUsername());
        em.flush();
    }
    @Test
    public void lock(){
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        List<Member> member11 = memberRepository.findLockByUsername("member1");


    }
    @Test
    public void callCustom(){
        List<Member> memberCustom = memberRepository.findMemberCustom();
        for (Member member : memberCustom) {
            System.out.println("member = " + member);
        }
    }
}
