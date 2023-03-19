package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",
        //basePackageClasses = AutoAppConfig.class,
        // 지정하지 않으면, componentscan 을 붙인 패키지부터 시작함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository2")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
