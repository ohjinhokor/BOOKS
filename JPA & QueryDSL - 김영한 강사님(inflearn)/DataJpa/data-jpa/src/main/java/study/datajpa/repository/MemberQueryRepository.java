package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

//굳이 모든 메서드를 사용자 정의 리포지토리를 통해 만들 필요가 있을까에 대해 고민할 필요가 있다.
// 만약 특정 화면 맞춤형 쿼리가 필요한 것이라면 핵심 비즈니스 로직과 분리하는 것도 좋은 방법이다.
// 이럴 경우 직접클래스를 만들어서 거기에서 화면에 필요한 쿼리를 날리도록 하는 방법이 있다.
// 예시
@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final EntityManager em;

    List<Member> findAllMembers() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }

}
// 이런식으로 직접 클래스로 구현한 후 필요한 곳에서 Autowired로 받아서 사용한다.