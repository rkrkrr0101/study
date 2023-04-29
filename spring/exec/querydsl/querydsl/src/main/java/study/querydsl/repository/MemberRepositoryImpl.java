package study.querydsl.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory qf;


    @Override
    public List<MemberTeamDto> search(MemberSearchCondition cond) {
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

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition cond, Pageable pageable) {
        List<MemberTeamDto> results = qf.select(new QMemberTeamDto(
                        member.id.as("memberId"), member.username, member.age,
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> countQuery = qf
                .select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(cond.getUsername()),
                        teamNameEq(cond.getTeamName()),
                        ageGoe(cond.getAgeGoe()),
                        ageLoe(cond.getAgeLoe())
                );

        return PageableExecutionUtils.getPage(results,pageable,()->countQuery.fetchOne());
        //return new PageImpl<>(results,pageable,countQuery.fetchOne());
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
