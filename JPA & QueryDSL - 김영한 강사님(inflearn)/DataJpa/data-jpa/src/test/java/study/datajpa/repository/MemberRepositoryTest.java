package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember(){
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass()); // 이를 통해 스프링 datajpa가 구현체를 만들어서 주입했음을 알 수 있음.
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD(){

            // 정석적인 테스트코드 스타일은 아니라고 함. 공부를 위한 간단한 테스트 코드임

            Member member1 = new Member("member1");
            Member member2 = new Member("member2");

            memberRepository.save(member1);
            memberRepository.save(member2);

            // 단건 조회
            // 실제로 테스트코드를 작성할 때는 get을 쓰는게 썩 좋은 방법이 아닌듯?
            Member findMember1 = memberRepository.findById(member1.getId()).get();
            Member findMember2 = memberRepository.findById(member2.getId()).get();

            assertThat(findMember1).isEqualTo(member1);
            assertThat(findMember2).isEqualTo(member2);

//        findMember1.setUsername("Hi Hi new Member");

            //리스트 조회
            List<Member> all = memberRepository.findAll();
            assertThat(all.size()).isEqualTo(2);

            //카운트 검증
            long count = memberRepository.count();
            assertThat(count).isEqualTo(2);

            //삭제 검증
            memberRepository.delete(member1);
            memberRepository.delete(member2);

            List<Member> deltedCount = memberRepository.findAll();
            assertThat(deltedCount.size()).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThan(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);
        m1.setUsername("hihihi");

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);

    }

    // 쿼리 메소드 기능 - JPA NamedQuery
    @Test
    public void findByUserName(){

        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);


        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    // 쿼리 메소드 기능 - 레포지토리 메소드에 직접 쿼리 정의하기
    @Test
    public void findUser(){

        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);


        List<Member> result = memberRepository.findUser("AAA",10);
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    // 쿼리 메소드 기능 - 값, DTO 조회하기
    @Test
    public void findUsernameList(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("\n\n s = " + s + "\n\n");
        }
    }

    @Test
    public void findMemberDto(){
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(teamA);
        memberRepository.save(m1);


        List<MemberDto> memberList = memberRepository.findMemberDto();
        for (MemberDto memberDto : memberList) {
            System.out.println("\n\n memberDto = " + memberDto);
        }
    }

    // 쿼리 메소드 기능 - 파라미터 바인딩(Collection을 파라미터로 받는 예시)
    @Test
    public void findByNames(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {

            System.out.println("\n\nmember = " + member + "\n\n");
        }
    }

    // 쿼리 메소드 기능 - 반환 타입
    @Test
    public void returnType(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> findMemberList = memberRepository.findMemberListByUsername("AAA");
        Member findMember = memberRepository.findMemberByUsername("AAA");
        Optional<Member> findOptionalMember = memberRepository.findOptionalMemberByUsername("AAA");

        // 반환타입에 대한 설명
        // list일 떄는 절대로 null이 아니다. 따라서 if(result==null)->이런거 할 필요 없음. 좋은 코드도 아님
        // optional일 때랑, optional이 아닐 때 null을 처리하는 방식이 다르다.

        //  <<<<Optional을 쓰는 것이 국룰!!!!>>>>

    }

    // 쿼리 메소드 기능 - 스프링 데이터 JPA 페이징과 정렬
    @Test
    public void paging(){

        //given

        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",10));
        memberRepository.save(new Member("member7",10));
        memberRepository.save(new Member("member8",10));

        memberRepository.save(new Member("member3",10));
        memberRepository.save(new Member("member4",10));
        memberRepository.save(new Member("member5",10));

        int age =10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));


        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        // 반환 예시 (Entity를 그대로 반환하는 것은 절대 금지임. dto로 반환해야함)
        // 이런식으로 dto를 반환하면 된다.
        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        //then
        List<Member> content = page.getContent();
        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(7);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(3);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();


        for (Member member : content) {
            System.out.println("\n\nmember = " + member+"\n\n");
        }
    }

//    @Test
//    public void slicing(){
//        //given
//
//        memberRepository.save(new Member("member1",10));
//        memberRepository.save(new Member("member2",10));
//        memberRepository.save(new Member("member7",10));
//        memberRepository.save(new Member("member8",10));
//
//        memberRepository.save(new Member("member3",10));
//        memberRepository.save(new Member("member4",10));
//        memberRepository.save(new Member("member5",10));
//
//        int age =10;
//
//        PageRequest pageRequest = PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "username"));
//
//        //when
//        Slice<Member> page = memberRepository.findByAge(age, pageRequest);
//
//        //then
//        List<Member> content = page.getContent();
//        assertThat(content.size()).isEqualTo(4);
////        assertThat(page.getTotalElements()).isEqualTo(7);
//        assertThat(page.getNumber()).isEqualTo(0);
////        assertThat(page.getTotalPages()).isEqualTo(3);
//        assertThat(page.isFirst()).isTrue();
//        assertThat(page.hasNext()).isTrue();
//
//        for (Member member : content) {
//            System.out.println("\n\nmember = " + member+"\n\n");
//        }
//    }

    // 쿼리 메소드 기능 - 벌크성 수정 쿼리
    @Test
    public void bulkUpdate(){

        //given
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",19));
        memberRepository.save(new Member("member3",20));
        memberRepository.save(new Member("member4",22));
        memberRepository.save(new Member("member5",40));

        //when
        int resultCount = memberRepository.bulkAgePlus(20);

        // 벌크 연산 이후에는 db에 반영하는 작업이 꼭 있어야한다. 또는 @Modifying에 clearAutomatically를 true로 하면 된다!
        em.flush();
        em.clear();


        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0);
        System.out.println("member5 = " + member5); // 벌크연산은 영속성 컨텍스트에 영향을 끼치지 않음을 확인할 수 있음.

        //then
        assertThat(resultCount).isEqualTo(3);
    }

    //쿼리 메소드 기능 - 엔티티 그래프
    @Test
    public void findMemberLazy(){

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        Member member1 = new Member("member1",10,teamA);
        Member member2 = new Member("member2",20,teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());

            // proxy가 사용됨을 확인할 수 있음
            System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());

            // 여기에서 n+1문제가 생김
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());;
        }
    }

    //쿼리 메소드 기능 - JPA Hint&Lock
    @Test
    public void queryHint(){

        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2"); // 해당 함수는 repository에서 readonly로 되어있기 때문에 변경되지 않음

    }

    @Test
    public void lock(){

        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");
    }

    // 확장 기능 - 사용자 정의 리포지토리 구현
    // 사용자 정의 메서드 기능을 사용하는 예시 - 나가는 쿼리를 확인해보자
    @Test
    public void callCustom() {
        List<Member> memberCustom = memberRepository.findMemberCustom();
    }

    // 나머지 기능들 - projections
    @Test
    public void projections(){
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        List<UsernameOnly> result = memberRepository.findProjectionsByUsername("m1");
        for (UsernameOnly usernameOnly : result) {
            System.out.println("usernameOnly = " + usernameOnly.getUsername());
        }
    }

    // 나머지 기능들 - projections
    @Test
    public void projections2(){
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        List<UsernameOnlyDto> result = memberRepository.findProjections2ByUsername("m1");
        for (UsernameOnlyDto usernameOnlyDto : result) {
            System.out.println("usernameOnlyDto = " + usernameOnlyDto.getUsername());
        }
    }

    // 나머지 기능들 - projections
    // 중첩 구조일 때 member.Team.name처럼 다른 객체는 최적화x. outer join으로 다 가져온다.
    @Test
    public void projections3(){
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        List<NestedClosedProjections> result = memberRepository.findProjections3ByUsername("m1");

        for (NestedClosedProjections nestedClosedProjections : result) {
            System.out.println("nestedClosedProjections.getUsername() = " + nestedClosedProjections.getUsername());
            System.out.println("nestedClosedProjections.getTeam().getName() = " + nestedClosedProjections.getTeam().getName());
        }
    }


    // 나머지 기능들 - 네이티브 쿼리
    @Test
    public void nativeQuery(){
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        Member result = memberRepository.findByNativeQuery("m1");
        System.out.println("result = " + result);
    }

    @Test
    public void nativeQueryProjection(){
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        Page<MemberProjection> result = memberRepository.findByNativeProjection(PageRequest.of(0, 10));
        List<MemberProjection> content = result.getContent();
        for (MemberProjection memberProjection : content) {
            System.out.println("\n\n\n");
            System.out.println("memberProjection.getUsername() = " + memberProjection.getUsername());
            System.out.println("memberProjection.getTeamName() = " + memberProjection.getTeamName());
        }
    }
}
