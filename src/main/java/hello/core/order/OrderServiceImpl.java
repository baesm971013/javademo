package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.member.Member;

//client code
public class OrderServiceImpl implements OrderService{
    // 이걸 내가 직접 바꾸는게 문제 가 있음 추상화를 해야하는 것이
    // 관심사를 분리를 해야한다
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    //interface에만 오로지 의존한다
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        Order order = new Order(memberId, itemName, itemPrice, discountPrice);
        return order;
    }
}
