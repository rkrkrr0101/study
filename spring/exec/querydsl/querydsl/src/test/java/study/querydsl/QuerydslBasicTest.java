package study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {
    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory ;

    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(em);
        Team teamA=new Team("TeamA");
        Team teamB=new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1=new Member("member1",10,teamA);
        Member member2=new Member("member2",20,teamA);
        Member member3=new Member("member3",30,teamB);
        Member member4=new Member("member4",40,teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL(){
        String qlString = "select m from Member m " +
                "where m.username=:username";
        Member singleResult = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(singleResult.getUsername()).isEqualTo("member1");
    }
    @Test
    public void startQuerydsl(){
        Member member1 = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search(){
        Member member11 = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1").and(member.age.eq(10)))
                .fetchOne();

        assertThat(member11.getUsername()).isEqualTo("member1");

    }
    @Test
    public void searchAndParam(){
        Member member11 = queryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1")
                        ,(member.age.eq(10))
                )
                .fetchOne();

        assertThat(member11.getUsername()).isEqualTo("member1");

    }
    @Test
    public void resultFetch(){
        List<Member> fetchabc = queryFactory.selectFrom(member)
                .fetch();
//        Member member1 = queryFactory.selectFrom(member)
//                .fetchOne();
//        Member member2 = queryFactory.selectFrom(member)
//                .fetchFirst();
        int size = fetchabc.size();

        System.out.println("size = " + size);
        int size1aaa = queryFactory.selectFrom(member)
                .fetch().size();
        System.out.println("size1aaa = " + size1aaa);

    }
    @Test
    public void sort(){
        em.persist(new Member(null,100));
        em.persist(new Member("member5",100));
        em.persist(new Member("member6",100));
        List<Member> fetch = queryFactory.selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();
        for (Member fetch1 : fetch) {
            System.out.println("fetch1 = " + fetch1);
        }
    }
    @Test
    public void paging1(){
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();
        for (Member fetch1 : fetch) {
            System.out.println("fetch1 = " + fetch1);
        }
    }

    @Test
    public void aggregation(){
        List<Tuple> result = queryFactory.select(
                    member.count(),
                    member.age.sum(),
                    member.age.avg(),
                    member.age.max()
                )
                .from(member)
                .fetch();
        Tuple tuple = result.get(0);
        Long count = tuple.get(member.count());
        System.out.println("count = " + count);

    }
    @Test
    public void group(){
        List<Tuple> result = queryFactory.select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();
        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("TeamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);
        assertThat(teamB.get(team.name)).isEqualTo("TeamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }
}
