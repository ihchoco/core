package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        /*
            스프링 생성 방법(스프링은 모두 ApplicationContext로 시작한다)
            Configuration을 가져오려면 new AnnotationConfigApplicationContext(AppConfig.class); 작성
            이렇게 하면 @Bean 등록된 객체들을 생성된 applicationContext를 사용해서 가져올 수 있다

            applicationContext.getBean("memberService", MemberService.class);
            여기서 memberService 이름은 AppConfig파일에 메소드명(memberService)이 Bean등록될 때 객체 이름으로 등록이 된다

            applicationContext.getBean("Bean이름", Bean타입)
        */
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member: "+member.getName());
        System.out.println("find member : "+findMember.getName());

    }
}
