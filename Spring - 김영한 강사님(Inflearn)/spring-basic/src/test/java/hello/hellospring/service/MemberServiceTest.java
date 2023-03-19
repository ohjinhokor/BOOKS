package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
class MemberServiceTest {

    MemberService memberservice;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberservice = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearstore();
    }

    @Test
    void join_회원가입() {

    //given
        Member member = new Member();
        member.setName("spring");

    //when
        Long saveId = memberservice.join(member);

        //then
        Member findMember = memberservice.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test()
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");


        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberservice.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다") ;


//        assertThrows(NullPointerException.class, () -> memberservice.join(member2));
//        try {
//            memberservice.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//           assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다asdfasdf");
//        }
//
//

        
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}