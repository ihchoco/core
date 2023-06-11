package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLOutput;

@Configuration //스프링에서는 설정정보를 @Configuration 어노테이션 추가해주어야 한다
public class AppConfig {

    /*
        우리가 기대하는 호출 예상 시나리오
        1. call AppConfig.memberSerivce
        2. call AppConfig.memberRepository
        3. call AppConfig.memberRepository
        4. call AppConfig.orderService
        5. call AppConfig.memberRepository

        순서는 바뀔수 있지만 중요한건 memberRepository가 2번 수행된다는 것이다
     */

    @Bean //@Bean을 추가해주면 이게 모두 스프링 컨테이너라는곳에 자동으로 등록이 된다
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl( memberRepository(), discountPolicy() );
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("call AppConfig.discountPolicy");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}