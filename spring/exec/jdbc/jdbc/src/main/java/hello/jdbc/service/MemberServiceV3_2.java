package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;


@Slf4j
public class MemberServiceV3_2 {
    private final MemberRepositoryV3 memberRepository;
    //private final PlatformTransactionManager transactionManager;
    private final TransactionTemplate txTemplate;

    public MemberServiceV3_2(MemberRepositoryV3 memberRepository, PlatformTransactionManager tm) {
        this.memberRepository = memberRepository;
        this.txTemplate=new TransactionTemplate(tm);
    }

    public void accountTransfer(String fromId, String toId, int money) {
        txTemplate.executeWithoutResult((status)->{
            bizLogic( fromId, toId, money);
        });
    }

    private void bizLogic( String fromId, String toId, int money) {
        Member fromMember = memberRepository.findById( fromId);
        Member toMember = memberRepository.findById( toId);

        memberRepository.update( fromId,fromMember.getMoney()- money);
        validation(toMember);
        memberRepository.update( toId,toMember.getMoney()+ money);
    }

    private static void release(Connection con) {
        if (con !=null){
            try{
                con.setAutoCommit(true);
                con.close();
            }catch (Exception e){
                log.info("error",e);
            }
        }
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체예외");
        }
    }
}
