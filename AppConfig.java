package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring Container
public class AppConfig {

    @Bean // injection method
    public MemberService memberservice() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderservice() {
        return new OrderServiceImpl(memberRepository(), discountpolicy());
    }

    @Bean
    public DiscountPolicy discountpolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
