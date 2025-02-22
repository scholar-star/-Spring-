package hello.core.autowired;

import hello.core.discount.DiscountPolicy;
import hello.core.AutoAppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L,"name", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
    
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        discountService.discount(member, 10000, "fixDiscountPolicy");
    }
    
    @Component // discountService의 Component : 생성자 Autowired 가능
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = "+policyMap);
            System.out.println("policies = "+policies);
        }

        public int discount(Member member, int discountPrice, String policyName) {
            DiscountPolicy discountPolicy = policyMap.get(policyName);
            return discountPolicy.discount(member, discountPrice);
        }
    }
}
