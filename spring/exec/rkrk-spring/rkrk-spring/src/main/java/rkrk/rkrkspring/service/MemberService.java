package rkrk.rkrkspring.service;

import jakarta.transaction.Transactional;
import rkrk.rkrkspring.domain.Member;
import rkrk.rkrkspring.repo.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        long start = System.currentTimeMillis();

        try {
            valduplicate(member);

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            System.out.println("join=" + (finish - start));
        }


    }

    private void valduplicate(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
