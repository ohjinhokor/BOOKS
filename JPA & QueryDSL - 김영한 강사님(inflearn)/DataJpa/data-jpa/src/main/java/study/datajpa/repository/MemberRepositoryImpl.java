package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
//@RequiredArgsConstructor -> 이거 사용해도 됨

//확장기능 - 사용자정의 리포지토리 구현시 주의 사항
// 이 클래스의 이름처럼, 커스텀 해서 들어가기를 바라는 Repository와 이름을 맞춰야한다.(+Impl)
// 이 경우 MemberRepository에 들어가기를 바라므로 MemberRepositoryImpl이라고 이름을 지어야한다.
// implements 다음 나오는 Repository이름은 상관 없음
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Autowired
    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
