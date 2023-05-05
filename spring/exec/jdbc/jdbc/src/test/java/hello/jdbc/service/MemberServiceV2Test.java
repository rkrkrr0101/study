package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.repository.MemberRepositoryV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceV2Test {
    public static final String MEMBER_A="memberA";
    public static final String MEMBER_B="memberB";
    public static final String MEMBER_EX="ex";

    private MemberRepositoryV2 memberRepository;
    private MemberServiceV2 memberService;

    @BeforeEach
    void before(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        memberRepository=new MemberRepositoryV2(dataSource);
        memberService=new MemberServiceV2(memberRepository,dataSource);
    }
    @AfterEach
    void After(){
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }
    @Test
        //@DisplayName("정상이체")
    void transferTest() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        memberService.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000);

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());

        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);

    }
    @Test

    void transferEXTest(){
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        assertThatThrownBy(()->memberService.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000))
                .isInstanceOf(IllegalStateException.class) ;

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());

        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberB.getMoney()).isEqualTo(10000);



    }


}