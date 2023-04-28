package study.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.*;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {
    private final EntityManager em;
    private final JPAQueryFactory qf;


    public void save(Member member){
        em.persist(member);

    }
    public Optional<Member> findById(Long id){
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public List<Member> findAll_Querydsl(){
        return qf.selectFrom(member)
                .fetch();
    }
    public List<Member> findByUsername(String username){
        return em.createQuery("select m from Member m where username=:username",Member.class)
                .setParameter("username",username)
                .getResultList();
    }
    public List<Member> findByUsername_querydsl(String username){
        return qf.selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }
    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition cond){
        BooleanBuilder builder= new BooleanBuilder();
        if (hasText(cond.getUsername())) {

            builder.and(member.username.eq(cond.getUsername()));
        }
        if(hasText(cond.getTeamName())){
            builder.and((team.name.eq(cond.getTeamName())));
        }
        if (cond.getAgeGoe() != null){
            builder.and(member.age.goe(cond.getAgeGoe()));
        }
        if (cond.getAgeLoe() != null){
            builder.and(member.age.loe(cond.getAgeLoe()));
        }
        return qf.select(new QMemberTeamDto(
                    member.id.as("memberId"), member.username,member.age,
                        team.id.as("teamId"), team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }

    public List<MemberTeamDto> search(MemberSearchCondition cond){
        return qf.select(new QMemberTeamDto(
                        member.id.as("memberId"), member.username,member.age,
                        team.id.as("teamId"), team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(cond.getUsername()),
                        teamNameEq(cond.getTeamName()),
                        ageGoe(cond.getAgeGoe()),
                        ageLoe(cond.getAgeLoe())
                        )
                .fetch();
    }

    private BooleanExpression usernameEq(String username) {
        if (hasText(username)){
            return member.username.eq(username);
        }
        return null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        if(hasText(teamName)){
            return team.name.eq(teamName);
        }
        return null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        if(ageGoe!=null){
            return member.age.goe(ageGoe);
        }
        return null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        if(ageLoe!=null){
            return member.age.loe(ageLoe);
        }
        return null;
    }


}
