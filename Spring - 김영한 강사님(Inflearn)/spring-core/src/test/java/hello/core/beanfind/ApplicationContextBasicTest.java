package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        MemberRepository memberRepository = memberService.getMemberRepository();
        assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);
        MemberRepository memberRepository1 = ac.getBean("memberRepository", MemberRepository.class);
        System.out.println("memberRepository1 = " + memberRepository1);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 역할에 의존하도록 짜야하므로 좋은 코드는 아닐 수도
//    @Test
//    @DisplayName("구체 타입으로 조회")
//    void findBeanByName2(){
//        MemberServiceImpl memberService = ac.getBean("memberSerivce", MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
//    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //getBean("Xxxxx", MemberService.class);
        //MemberService xxxxx = ac.getBean("Xxxxx", MemberService.class);

        assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("Xxxxx", MemberService.class));
    }
}
