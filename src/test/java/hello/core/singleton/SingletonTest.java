package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링없는 순수한 di 컨테이너")
    void t1(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        System.out.println(memberService2);
        System.out.println(memberService);
        Assertions.assertThat(memberService2).isNotEqualTo(memberService);
    }
    @Test
    void t2(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        System.out.println(singletonService1);
        System.out.println(singletonService2);
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
    @Test
    void t3(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        MemberService memberService2 = applicationContext.getBean("memberService",MemberService.class);

        Assertions.assertThat(memberService2).isSameAs(memberService);
    }

}
