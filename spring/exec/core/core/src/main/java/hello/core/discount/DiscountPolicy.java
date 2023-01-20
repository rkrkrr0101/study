package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    //return 할인금액을 리턴함
    int discount(Member member,int price);
}
