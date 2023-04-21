package study.datajpa.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberRepositoryCustom {

    //List<Member> findByUsername(String username);
    List<Member> findByUsernameAndAgeGreaterThan(String username,int age);

    List<Member> findByAgeGreaterThan(int age);

    //@Query(name="Member.findByUsername")
    List<Member> finduser(@Param("username")String username);
    @Query("select m from Member m where m.username=:username and m.age=:age")
    List<Member> findUserAge(@Param("username") String username,@Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id,m.username,t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findMemberOptionalByUsername(String username);

    Page<Member> findByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m",
    countQuery = "select count(m.username) from Member m")
    Page<Member> findAllCountBy(Pageable pageable);

    @Modifying(clearAutomatically=true)
    @Query("update Member m set m.age=m.age+1 where m.age>=:age")
    int bulkAgePlus(@Param("age")int age);

    @Query("select m from Member m")
    @EntityGraph(attributePaths = {"team"})
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();
    @Override
    @EntityGraph(attributePaths = {"team"})
    Page<Member> findAll(Pageable pageable);
    @QueryHints(value=@QueryHint(name = "org.hibernate.readOnly",value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

}
