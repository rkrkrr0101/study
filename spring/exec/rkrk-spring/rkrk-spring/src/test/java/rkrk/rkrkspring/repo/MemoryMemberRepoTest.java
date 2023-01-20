package rkrk.rkrkspring.repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rkrk.rkrkspring.domain.Member;

import java.util.List;

public class MemoryMemberRepoTest {
    MemoryMemberRepo repo = new MemoryMemberRepo();

    @AfterEach
    public void afterEach() {
        repo.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        String aa = "sss";
        member.setName(aa);

        repo.save(member);
        Member res = repo.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(res);
    }

    @Test
    public void findByName() {
        Member member2 = new Member();
        member2.setName("spr2");
        repo.save((member2));

        Member member3 = new Member();
        member3.setName("spr3");
        repo.save((member3));

        Member res2 = repo.findByName("spr2").get();
        Assertions.assertThat(member2).isEqualTo(res2);
        res2 = repo.findByName("spr3").get();
        Assertions.assertThat(member3).isEqualTo(res2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spr1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spr2");
        repo.save(member2);

        List<Member> res = repo.findAll();
        Assertions.assertThat(res.size()).isEqualTo(2);
    }


}
