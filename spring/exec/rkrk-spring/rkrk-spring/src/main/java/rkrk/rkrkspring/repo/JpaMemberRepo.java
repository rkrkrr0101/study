package rkrk.rkrkspring.repo;

import jakarta.persistence.EntityManager;
import rkrk.rkrkspring.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepo implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepo(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> res = em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name).getResultList();
        return res.stream().findAny();


    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
