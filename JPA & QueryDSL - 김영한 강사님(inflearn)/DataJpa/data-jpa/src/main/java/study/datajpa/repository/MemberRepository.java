package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {


    //DATAJPA가 자동으로 함수를 만들어줌.. 이거는 진짜 혁명적인데....?
    // 엔티티의 필드 값과 함수의 이름이 같아야 함
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //Top3 예시, 여기서 Hello는 크게 의미가 없는 단어임
    List<Member> findTop3HelloBy();

    //NamedQuery는 실무에서 잘 안씀. 이 다음 배우는 레포지토리에 직접 @Query를 하는 기능을 많이 사용
    @Query(name="Member.findByUsername") // @Query가 없어도 인지해서 Member.findByUsername을 찾아보게됨
    //Named쿼리의 가장 큰 장점은 컴파일 시점에 오류를 잡아낸다는 점이다. 굉장히 중요함
    List<Member> findByUsername(@Param("username") String username);

    // 쿼리 메소드 기능 - 리포지토리 메소드에 쿼리 정의하기
    // 이름이 없는 namedQuery와 같아서 실행될 때 문법오류가 바로 잡힘.
    @Query("select m from Member m where m.username = :username and m.age =:age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 쿼리 메소드 기능 - 값, DTO 조회하기
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // 쿼리 메소드 기능 - 파라미터 바인딩(Collection을 파라미터로 받는 예시) - 은근히 실무에서 자주 사용함
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    // 쿼리 메소드 기능 - 반환 타입 find다음의 단어는 크게 상관 없는 듯 By 다음 나오는 필드 이름과 반환 값이 중요함
    List<Member> findMemberListByUsername(String username);

    Member findMemberByUsername(String username);

    Optional<Member> findOptionalMemberByUsername(String username);

    // 쿼리 메소드 기능 - 스프링 데이터 JPA 페이징과 정렬
    // 주의!!!! 페이지는 인덱스가 1이 아니라 0부터 시작한다
    Page<Member> findByAge(int age, Pageable pageable);

//    Slice<Member> findByAge(int age, Pageable pageable);

    // 쿼리 메소드 기능 - 스프링 데이터 JPA 페이징과 정렬 - 카운트 쿼리를 날리는 더 좋은 방법

    @Query(value = "select m from Member m left join m.team",
            countQuery = "select count(m) from Member m")
    Page<Member> findByAgeForCount(int age, Pageable pageable);


    // 쿼리 메소드 기능 - 벌크성 수정 쿼리

    @Modifying(clearAutomatically = true) // executeUpdate의 기능을 하는 어노테이션.
    //clearAutomatically = true로 영속성 컨텍스트 초기화 함
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age")int age);


    //쿼리 메소드 기능 - Entity Graph
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();


    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);


    // 쿼리 메소드 기능 - JPA Hint, Lock
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    //Lock 예시
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    // 나머지 기능들 - projections
    List<UsernameOnly> findProjectionsByUsername(@Param("username") String username);

    List<UsernameOnlyDto> findProjections2ByUsername(@Param("username") String username);

    List<NestedClosedProjections> findProjections3ByUsername(@Param("username") String username);


    // 나머지 기능들 - 네이티브 쿼리
    // sort가 정상동작하지 않을 수도 있음
    // JPQL처럼 애플리케이션 로딩 시점에 문법 확인 불가
    // 동적 쿼리 불가
    // 결론적으로는 잘 사용하지 않음.. 사용한다면 JdbcTemplate이나 MyBatis권장

    @Query(value = "select * from member where username =?", nativeQuery = true)
    Member findByNativeQuery(String username);

    //최근에 projections가 nativeQuery에서 사용할 수 있게 됨. 이건 좀 유용한 듯
    @Query(value = "select m.member_id as id, m.username, t.name as teamName "+
            "from member m left join team t",
            countQuery = "select count(*) from member"
            ,nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}