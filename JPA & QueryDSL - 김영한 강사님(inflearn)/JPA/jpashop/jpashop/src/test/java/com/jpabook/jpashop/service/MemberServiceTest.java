package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() {//throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");
        //System.out.println("member.getId() = " + member.getId());

        //when
        Long newMemberId = memberService.join(member);
        //System.out.println("newMemberId = " + newMemberId);
        //then
        //em.flush();
        assertEquals(member, memberRepository.findOne(newMemberId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
    //    try {   // 위의 expected 가 대체함
        memberService.join(member2); // 예외가 발생해야 한다
    //     } catch (IllegalStateException e) {
    //        return;

        //then

        fail("예외가 발생해야 한다");
    }
}
