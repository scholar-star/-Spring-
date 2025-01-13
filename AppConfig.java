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

// @Autowired : 자동으로 의존관계 주입(@Configuration없이도 싱글톤 유지)

@Configuration // Spring Container(바이트 조작 적용) - 없을 경우 싱글톤이 적용되지 않음
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 2번 이상 호출

    @Bean // injection method
    public MemberService memberservice() {
        System.out.println("call AppConfig.memberservice");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderservice() {
        System.out.println("call AppConfig.orderservice");
        return new OrderServiceImpl(memberRepository(), discountpolicy());
    }

    @Bean
    public DiscountPolicy discountpolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
