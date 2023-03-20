package jpabasic.ex1hellojpa.hellojpa.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabasic.ex1hellojpa.hellojpa.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor

public class HelloController {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Member member=new Member();
        member.setName("dfda");
        em.persist(member);

        Member findmember = em.find(Member.class, member.getId());
        return findmember.getName();
    }

}
