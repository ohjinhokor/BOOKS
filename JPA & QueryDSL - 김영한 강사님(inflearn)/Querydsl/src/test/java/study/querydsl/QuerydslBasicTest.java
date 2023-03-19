package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.*;

@SpringBootTest
@Transactional
@Commit
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // 초기화
        em.flush();
        em.clear();
    }

    @Test
    public void startJPQL() {
        //member1을 찾아라
        Member findMember = em.createQuery("select m from Member m where m.username =:username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {

//        QMember m = new QMember("m"); // 이 방법도 맞는 방법임

//        QMember m = QMember.member;

//        Member findMember = queryFactory // queryFactory는 전역변수로 선언되어있음음
//                .select(m)
//                .from(m)
//                .where(m.username.eq("member1")) //파라미터 바인딩 처리
//                .fetchOne();

//                기본 문법 - 기본 Q Type 활용
        Member findMember = queryFactory // queryFactory는 전역변수로 선언되어있음음
                .select(member) // 여기서 member는 QMember.member임, QMember는 전역변수로 선언되어 있음.
                .from(member)
                .where(member.username.eq("member1")) //파라미터 바인딩 처리
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    //기본 문법 - 검색 조건 쿼리
    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(member) //여기서 member는 QMember.member임(전역변수로 QMember가 있음)
                .where(member.username.eq("member1")
                        .and(member.age.between(10, 30)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

        // 조건 검색 예시
//        member.username.eq("member1") // username = 'member1'
//        member.username.ne("member1") //username != 'member1'
//        member.username.eq("member1").not() // username != 'member1'
//        member.username.isNotNull() //이름이 is not null
//        member.age.in(10, 20) // age in (10,20)
//        member.age.notIn(10, 20) // age not in (10, 20)
//        member.age.between(10,30) //between 10, 30
//        member.age.goe(30) // age >= 30
//        member.age.gt(30) // age > 30
//        member.age.loe(30) // age <= 30
//        member.age.lt(30) // age < 30
//        member.username.like("member%") //like 검색
//        member.username.contains("member") // like ‘%member%’ 검색
//        member.username.startsWith("member") //like ‘member%’ 검색
    }

    @Test
    public void searchAndParam() {
        Member findMember = queryFactory
                .selectFrom(member) //여기서 member는 QMember.member임(전역변수로 QMember가 있음)
                .where(
                        member.username.eq("member1"),
                        member.age.between(10, 30)
                )
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // 기본 문법 - 결과 조회
    @Test
    public void resultFetchTest() {
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        // 여기서 예외발생됨. 결과가 1개가 아니기 때문
        Member fetchOne = queryFactory
                .selectFrom(QMember.member)
                .fetchOne();

        System.out.println(fetchOne);

        Member fetchFirst = queryFactory
                .selectFrom(QMember.member)
                .fetchFirst();

//        공식문서에서는 fetch()를 권장함
        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        long total = results.getTotal();
        System.out.println("total =" + total);
        List<Member> content = results.getResults();

        //여기서는 쿼리가 count 쿼리가 나감
        long countTotal = queryFactory
                .selectFrom(member)
                .fetchCount();
    }

    //기본 문법 - 정렬

    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 올림차순(asc)
     * 단 2에서 회원 이름이 없으면 마지막에 출력(null last)
     */
    @Test
    public void sort() {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

    //기본 문법 - 페이징
    @Test
    public void paging() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) // 0부터 시작임
                .limit(2)
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging2() {
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) // 0부터 시작임
                .limit(2)
                .fetchResults();

        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults()).isEqualTo(2);
    }

    //기본 문법 - 집합
    @Test
    public void aggregation() {
        List<Tuple> result = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);

        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);

    }

    /**
     * 팀의 이름과 각 팀의 평균 연령을 구해라
     */
    @Test
    public void group() throws Exception {
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }

    //기본 문법 - 조인(기본 조인)

    /**
     * 팀 A에 소속된 모든 회원
     */
    @Test
    public void join() {
        List<Member> result = queryFactory
                .selectFrom(member)
                .join(member.team, team) // 여기서 team은 사실 QTeam.team이다
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    /**
     * 세타 조인
     * 회원의 이름이 팀 이름과 같은 회원을 조회
     */
    @Test
    public void theta_join() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));
        List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");
    }

    //기본 문법 - 조인(on절)
    /**
     * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA이 팀만 조인, 회원은 모두 조회
     * JPQL : select m, t from Member m left join m.team t on t.name = 'teamA'
     */
    @Test
    public void join_on_filtering() {
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 연관관계가 없는 엔티티 외부 조인
     * 회원의 이름이 팀 이름과 같은 대상 외부 조인
     */
    @Test
    public void join_on_no_relation() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    // 기본 문법 - 조인(페치 조인)
    @Test
    public void fetchJoinNo(){
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();


        //team이 프록시인지 아닌지 확인 하는 작업
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("페치 조인 미적용").isFalse();
    }

    @Test
    public void fetchJoinUse(){
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();


        //team이 프록시인지 아닌지 확인 하는 작업
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("페치 조인 적용").isTrue();
    }


    // 기본 문법 - Case문(대부분의 경우에서 db에서 보다는 코드 레벨에서 바꾸기를 추천)
   @Test
    public void basicCase(){
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void complexCase(){
        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    //기본 문법 - 상수,문자 더하기
    @Test
    public void constant(){
        List<Tuple> result = queryFactory
                .select(member.username, Expressions.constant("A")) // 무조건 username에 A가 들어감
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    //{username}_{age}로 출력됨
    @Test
    public void concat(){
        List<String> result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    // 기본 문법 - 서브 쿼리

    /**
     * 나이가 가장 많은 회원을 조회
     */
    @Test
    public void subQuery() throws Exception{

        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

        for (Member member1 : result) {
            System.out.println("member1 = " + member1);

        }

        assertThat(result).extracting("age")
                .containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원
     */
    @Test
    public void subQueryGoe() throws Exception{

        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(30, 40);
    }


    @Test
    public void subQueryIn() throws Exception{

        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions
                                .select(memberSub.age)
                                .from(memberSub)
                        .where(memberSub.age.gt(10))
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(20, 30, 40);
    }

    @Test
    public void selectSubQuery(){

        QMember memberSub = new QMember("memberSub");

        List<Tuple> result = queryFactory
                .select(member.username,
                        JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub))
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);

        }
    }

    // 중급 분법 - 프로젝션과 결과 반환 - 기본
    @Test
    public void simpleProjection(){
        List<String> reuslt = queryFactory
                .select(member.username)
                .from(member)
                .fetch();

        for (String s : reuslt) {
            System.out.println("s = " + s);
        }
    }

    /**
     * 주의점!!
     * tuple은 querydsl에서 사용하는 객체이다. 따라서 service에 tuple을 그대로 가져다 쓰는거는 service가 repository에 의존하는 현상이 생김.
     * tuple을 그냥 반환하기보다는 dto를 사용하는 것이 바람직하다!
     */
    @Test
    public void tupleProjection(){
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }


    // 중급 문법 - 프로젝션과 결과 반환 - DTO조회

    //jpql로 하는 경우
    @Test
    public void findDtoByJPQL(){
       List<MemberDto> result = em.createQuery("select new study.querydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
               .getResultList();

       for (MemberDto memberDto : result) {
           System.out.println("memberDto = " + memberDto);
       }
   }


   // Projections.bean을 사용하는 경우 getter와 setter가 필수적임
    @Test
    public void findDtoBySetter(){
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }


    // Projections.field를 사용하는 경우 getter와 setter 없어도 가능함
   @Test
    public void findDtoByField(){
        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByConstructor(){
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    //Dto의 필드명과 Entity의 필드명이 같지 않은 경우
    @Test
    public void findUserDto(){
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"),
                        member.age))
                .from(member)
                .fetch();

        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    //Dto의 필드명과 Entity의 필드명이 같지 않은 경우 2
    @Test
    public void findUserDtoBySubQeury() {
        QMember memberSub = new QMember("memberSub");

        // 복잡함. 복습할 때 밑에 부분 하나하나 잘 살펴보기!
        // 서브쿼리를 쓰는 예시임
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"),

                        ExpressionUtils.as(JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub),"age")
                ))
                .from(member)
                .fetch();
    }

    //@QueryProjection에 비한 단점 : 잘못된 인자가 생성자에 들어가도 런타임 시점에 오류를 잡을 수 있음
    @Test
    public void findUserDtoByConstructor() {
        List<UserDto> result = queryFactory
                .select(Projections.constructor(UserDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    // 중급 문법 - 프로젝션과 결과 반환 - @QueryProjection
    // DTO의 생성자에 @QueryProjection을 붙이는 방법을 사용한다
    //@QueryProjection의 장점 : dto와 매핑되지 않는 값이 들어올 경우 컴파일 에러를 통해 바로 찾을 수 있다는 것이다.(런타임 에러까지 가지 않는다)
    //@QueryProjection의 단점 : Q파일을 생성해야함. Dto가 Querydsl을 의존하게됨.
    @Test
    public void findDtoByQueryProjection(){
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    // 중급 문법 - BooleanBuilder 사용
    @Test
    public void dynamicQuery_BooleanBuilder(){
        String usernameParam = "member1";
//        Integer ageParam = 10;
        Integer ageParam = null;

        List<Member> result = searchMember1(usernameParam, ageParam);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();
        if(usernameCond !=null){
            builder.and(member.username.eq(usernameCond));
        }
        if(ageCond != null){
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }


    // 중급 문법 - where 다중 파라미터 사용. 김영한님 추천!
    @Test
    public void dynamicQuery_WhereParam(){
        String usernameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember2(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
                .selectFrom(member)
                // where안에 null이 들어가면 무시하게됨
//                .where(usernameEq(usernameCond), ageEq(ageCond))
                .where(allEq(usernameCond, ageCond))
                .fetch();
    }

    private BooleanBuilder usernameEq(String usernameCond) {
//        if(usernameCond == null){
//            return null;
//        }
//        return member.username.eq(usernameCond);

        return usernameCond == null ? new BooleanBuilder() : new BooleanBuilder(member.username.eq(usernameCond));
    }

    private BooleanBuilder ageEq(Integer ageCond) {
        return ageCond == null ? new BooleanBuilder() : new BooleanBuilder(member.age.eq(ageCond));
    }

    private BooleanBuilder allEq(String usernameCond, Integer ageCond){
        return usernameEq(usernameCond).and(ageEq(ageCond));
    }


    /**
     * 밑의 부분은 동적 쿼리 생성시 null처리를 깔끔하게 처리하는 방법 예시
     */
//    private BooleanBuilder ageEq(Integer age) {
//        return nullSafeBuilder(() -> member.age.eq(age));
//    }
//
//    private BooleanBuilder roleEq(String roleName) {
//        return nullSafeBuilder(() -> member.roleName.eq(roleName));
//    }
//
//    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
//        try {
//            return new BooleanBuilder(f.get());
//        } catch (IllegalArgumentException e) {
//            return new BooleanBuilder();
//        }
//    }


    // 중급 문법 - 삭제, 벌크 연산
    @Test
    public void bulkUpdate(){

        //member1 = 10 -> 비회원
        //member2 = 20 -> 비회원
        //member3 = 30 -> 유지
        //member4 = 40 -> 유지

        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        //주의!
        //bulk와 관계된 쿼리는 영속성컨텍스트의 값을 바꾸는 것이 아니라 DB에 바로 sql이 날아감
        //count는 영향을 받은 row 수
       long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        /**
         *
         * 1 차 캐시(영속성 컨텍스트)에는 값이 변경되지 않았으므로
         * 중간에 em.flush()를 하지 않으면
         * 위 쿼리의 결과인 result의 member1, member2의 이름은 변경되지 않은 것을 확인 할 수 있다.
          */

        System.out.println("before flush");
        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }

        em.flush();
        em.clear();

        List<Member> result2 = queryFactory
                .selectFrom(member)
                .fetch();

        System.out.println("after flush");
        for (Member member2 : result2) {
            System.out.println("member2 = " + member2);
        }
    }

    @Test
    public void bulkAdd(){
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
    }

    @Test
    public void bulkDelete(){
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(10))
                .execute();
    }

    // 중급 문법 - sql function 호출하기
    @Test
    public void sqlFunction(){

        //username에서 member라는 단어를 M으로 치환하여 반환함
        List<String> result = queryFactory
                .select(Expressions.stringTemplate(
                        "function('replace', {0}, {1}, {2})",
                        member.username, "member", "M"))
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("queryFactory = " + queryFactory);
        }
    }

    @Test
    public void sqlFunction2(){
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .where(member.username.eq(
                        Expressions.stringTemplate("function('lower', {0})", member.username)))
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
}
