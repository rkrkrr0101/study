package rkrk.rkrkspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rkrk.rkrkspring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepo extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
