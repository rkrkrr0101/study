package study.datajpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.TeamRepository;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id")Member member){
        return member.getUsername();
    }
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size=5,sort = "username") Pageable pageable){
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDto> map = page.map
                (m -> new MemberDto(m.getId(), m.getUsername(), m.getTeam().getName()));
        return map;
    }


    @PostConstruct
    public void init(){
        Team teamA = new Team("TeamA");
        teamRepository.save(teamA);
        memberRepository.save(new Member("memberA",12,teamA));
        memberRepository.save(new Member("memberA1",12,teamA));
        memberRepository.save(new Member("memberA2",12,teamA));
        memberRepository.save(new Member("memberA3",12,teamA));
        memberRepository.save(new Member("memberA4",12,teamA));
    }
}
