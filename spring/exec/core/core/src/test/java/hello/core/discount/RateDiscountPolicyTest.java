package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy= new RateDiscountPolicy();

    @Test
    @DisplayName("vip10퍼할인")
    void vip_o(){
        //g
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //w
        int discount = discountPolicy.discount(member, 10000);
        //t
        assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("vip가 아니면 할인x")
    void vip_x(){
        //g
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);
        //w
        int discount = discountPolicy.discount(member, 10000);
        //t
        assertThat(discount).isEqualTo(0);
    }
}