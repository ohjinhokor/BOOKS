package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static jpql.MemberType.ADMIN;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

////            // 객체지향 쿼리 언어1 - 기본 문법 - 기본 문법과 쿼리 API
//            Member member = new Member();
//            member.setUsername("memberName");
//            member.setAge(10);
//            em.persist(member);
//
//            // typedquery와 query의 사용 예시(엄청 중요한 내용은 아님)
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username from Member m");
//
//            // 결과 조회 API
//            TypedQuery<Member> queryExample = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = queryExample.getResultList();// 결과가 없으면 빈 값 반환
//            String singleResult = query2.getSingleResult(); // 결과가 없거나 2개 이상이면 에러가 나옴
//
//            // 파라미터 바인딩
//            TypedQuery<Member> queryExample2 = em.createQuery(
//                    "select m from Member m where m.username = :username", Member.class);
//            queryExample2.setParameter("username", member.getUsername());
//            Member findMember = queryExample2.getSingleResult();
//            System.out.println("singResult = "+ findMember.getUsername());
//
//            // 실제로 사용하는 예시
//            Member findMember2 = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", member.getUsername())
//                    .getSingleResult();
//            System.out.println("findMember2 = "+ findMember2.getUsername());



//            // 객체지향 쿼리 언어1 - 기본 문법 - 프로젝션
//            // 엔티티 프로젝션
//            // createQuery를 통해 꺼내온 엔티티는 영속성 컨텍스트에 의해서 관리됨
//            List<Team> result = em.createQuery("select m.team from Member m join m.team t", Team.class)
//                    .getResultList();
//
//            // 임베디드 타입 프로젝션
//            List<Address> addressResult = em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();
//
//            // 스칼라 타입 프로젝션
//            // 값을 빼올 때 DTO를 사용하여 값을 가져오는 것이 가장 현명한 방법이다.
//
//            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) FROM Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("username = " + memberDTO.getUsername());
//            System.out.println("age ="+ memberDTO.getAge());


//            // 객체지향 쿼리 언어1 - 기본 문법 - 페이징
//            for(int i=0; i<100; i++){
//                Member member = new Member();
//                member.setUsername("memberName"+i);
//                member.setAge(i);
//                em.persist(member);
//            }
//            em.flush();
//            em.clear();
//
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result.size = "+ result.size());
//            for (Member member : result) {
//                System.out.println("member.age = "+member.getAge());
//            }


//            // 객체지향 쿼리 언어1 - 기본 문법 - 조인
//
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("memberName");
//            member.setAge(10);
//            member.setTeam(team);
//            em.persist(member);
//
//
//            Team team2 = new Team();
//            team.setName("example");
//            em.persist(team);
//
//            Member member2 = new Member();
//            member.setUsername("example");
//            member.setAge(10);
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            // inner join
//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            // outer join
//            String query2 = "select m from Member m left outer join m.team t";
//            List<Member> result2 = em.createQuery(query, Member.class)
//                    .getResultList();
//
//
//            // 세타 join
//            String query3 = "select m from Member m, Team t where m.username =t.name";
//            List<Member> result3 = em.createQuery(query, Member.class)
//                    .getResultList();
//            System.out.println("result3 = "+result3.size());
//
//            // 조인 대상 필터링
//            String query4 = "select m from Member m left join m.team t on t.name = 'teamA'";
//            List<Member> result4 = em.createQuery(query4, Member.class)
//                    .getResultList();
//
//            System.out.println("result4 = "+ result.size());
//
//
//            // 연관관계 없는 엔티티 외부 조인
//            String query5 = "select m from Member m left join Team t on m.username = t.name";
//            List<Member> result5 = em.createQuery(query4, Member.class)
//                    .getResultList();



            // 객체지향 쿼리 언어1 - 기본 문법 - 서브쿼리

//            // 서브쿼리 예시(From절의 서브쿼리는 안됨 - 비슷한 상황이 생긴다면 join으로 풀어서 해결해야한다)
//            String query1 = "select (select avg(m1.age) From Member m1) as avgAge from Member m left join Team t on m.username = t.name";
//            List<Member> result1 = em.createQuery(query1, Member.class)
//                    .getResultList();


//            // 객체지향 쿼리 언어1 - 기본 문법 - JPQL의 타입 표현
//
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("memberName");
//            member.setAge(10);
//            member.setTeam(team);
//            em.persist(member);
//
//
//            Team team2 = new Team();
//            team2.setName("example12");
//            em.persist(team2);
//
//            Member member2 = new Member();
//            member2.setUsername("example");
//            member2.setAge(10);
//            // ENUM 클래스 추가
//            member2.setType(ADMIN);
//            member2.setTeam(team2);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
////            String query = "select m.username, 'HELLO', TRUE FROM Member m where m.type = jpql.MemberType.ADMIN";
//            String query = "select m.username, 'HELLO', TRUE FROM Member m where m.type =:userType";
//            List<Object[]> result = em.createQuery(query)
//                    .setParameter("userType", ADMIN)
//                    .getResultList();
//
//            for (Object[] objects : result) {
//                System.out.println(objects[0]);
//                System.out.println(objects[1]);
//                System.out.println(objects[2]);
//            }



////             객체지향 쿼리 언어1 - 기본 문법 - 조건식
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("관리자");
//            member.setAge(10);
//            member.setTeam(team);
//            em.persist(member);
//
//
//            Team team2 = new Team();
//            team2.setName("example12");
//            em.persist(team2);
//
//            Member member2 = new Member();
//            member2.setUsername("example");
//            member2.setAge(10);
//            // ENUM 클래스 추가
//            member2.setType(ADMIN);
//            member2.setTeam(team2);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//
////            //기본 CASE식
////            String query = "select "+
////                    "case when m.age <= 10 then '학생요금' " +
////                    "when m.age >= 60 then '경로요금' " +
////                    "else '일반요금' "+
////                    "end " +
////                    "from Member m";
////            List<String> result = em.createQuery(query, String.class)
////                    .getResultList();
////
////            for (String s : result) {
////                System.out.println("s = "+ s);
////            }
//
//
////            // coalesce
////            String query2 = "select coalesce(m.username, '이름 없는 회원') from Member m ";
////            List<String> result2 = em.createQuery(query2, String.class)
////                    .getResultList();
////
////
////            for (String s : result2) {
////                System.out.println("s = "+ s);
////            }
//
//            //nullif (m.username이 '관리자'이면 null을 반환하게된다.)
//            String query2 = "select nullif(m.username, '관리자') from Member m ";
//            List<String> result2 = em.createQuery(query2, String.class)
//                    .getResultList();
//
//
//            for (String s : result2) {
//                System.out.println("s = "+ s);
//            }


//            //객체지향 쿼리 언어1 - 기본 문법 - JPQL 기본 함수
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("관리자");
//            member.setAge(10);
//            member.setTeam(team);
//            em.persist(member);
//
//
//            Team team2 = new Team();
//            team2.setName("example12");
//            em.persist(team2);
//
//            Member member2 = new Member();
//            member2.setUsername("example");
//            member2.setAge(10);
//            // ENUM 클래스 추가
//            member2.setType(ADMIN);
//            member2.setTeam(team2);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            // 쿼리 예시들
//            String query = "select 'a' || 'b' From Member m";
//            String query2 = "select substring(m.username, 2, 3) From Member m";
//            String query3 = "select size(t.members) From Team t";
//            List<String> result = em.createQuery(query2, String.class)
//                    .getResultList();
//
//
//            for (String s : result) {
//                System.out.println("s = "+ s);
//            }



            //객체지향 쿼리 언어2 - 중급 문법 - 경로 표현식
            // 경로표현식이란 -> 점을 찍어 객체 그래프를 탐색 하는 것
            // 1. 상태 필드, 2. 연관 필드 - (단일 값 연관 필드, 컬렉션 값 연관 필드)

            // 상태 필드 : 경로 탐색의 끝, 탐색 x  // ex) select m.username From Member m
            // 단일 값 연관 경로 : 묵시적 내부 조인(inner join) 발생, 탐색 O // ex) select m.team From Member m
            // 컬렉션 값 연관 경로 :묵시적 내부 조인 발생,  탐색 X // ex) select t.members From Team t -> 사용 거의 안함
            // From 절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능 하다 // ex) select m.username From Team t join t.members m
            // 묵시적인 내부 조인이 발생하도록 코드를 짜는 것은 좋지 않다.(단일 값 연관 경로도 묵시적 내부 조인이 발생한다는 것을 알자)

            // 결과적으로 하고 싶은 말은 묵시적 조인을 사용하지 말 것!!
            // 궁금증.. 묵시적 조인을 사용하지 않으면 어떤식으로 쿼리를 짜는 것이 옳은가..

            // select m from Member m join m.team t -> 명시적 조인
           // select m.team from Member m -> 묵시적 조인(내부 조인만 가능)



//            //페치조인 진짜 엄청중요하다!!!!
//            //객제지향 쿼리 언어2 - 중급 문법 - 페치조인
//
//            Team teamA = new Team();
//            teamA.setName("teamA");
//            em.persist(teamA);
//
//            Team teamB = new Team();
//            teamB.setName("teamB");
//            em.persist(teamB);
//
//
//            Member member1 = new Member();
//            member1.setUsername("회원1");
//            member1.setAge(10);
//            member1.setTeam(teamA);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("회원2");
//            member2.setAge(10);
//
//            member2.setType(ADMIN);
//            member2.setTeam(teamB);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setTeam(teamB);
//            em.persist(member3);
//
//            em.flush();
//            em.clear();
//
//
//            String before_query = "select m From Member m";
//
//            //페치조인!!
//            String query = "select m From Member m join fetch m.team";
//
////            List<Member> before_result = em.createQuery(before_query, Member.class).getResultList();
//
////            for (Member member : before_result) {
////                System.out.println("member = " + member.getUsername() + member.getTeam());
////            }
////            //회원1, 팀A(SQL)
////            //회원2, 팀B(SQL)
////            //회원3, 팀B(SQL)
////
////            //만약 회원 100명이 다 다른팀이라면 총 sql이 101번 나감. N+1문제 발생!!
//
//
////            // 페치조인을 사용한 경우
////            List<Member> fetch_result = em.createQuery(query, Member.class).getResultList();
////
////            for (Member member : fetch_result) {
////                System.out.println("member = " + member.getUsername() +"," + member.getTeam().getName());
////            }
//
//
////            // 1:N 관계의 페치 조인
////            // TeamA - 회원1
////            // TeamB - 회원2
////            // TeamB - 회원3
////            // 이런식으로 TeamB의 row가 중복되어 나타난다.
////            String query3 = "select t From Team t join fetch t.members";
////
////            List<Team> result = em.createQuery(query3, Team.class).getResultList();
////            for (Team team : result) {
////                System.out.println("team = " + team.getName() + ", member = " + team.getMembers().size());
////                for(Member member : team.getMembers()){
////                    System.out.println(" -> member = " + member.getUsername());
////                }
////            }
//
//            // 1:N 관계의 중복을 없애는 방법 1
//            String query4 = "select distinct t From Team t join fetch t.members";
//            List<Team> result = em.createQuery(query4, Team.class).getResultList();
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + ", member = " + team.getMembers().size());
//                for(Member member : team.getMembers()){
//                    System.out.println(" -> member = " + member.getUsername());
//                }
//            }

            //객제지향 쿼리 언어2 - 중급 문법 - 페치조인
            // 페치 조인의 특징과 한계
            // 페치 조인 대상에는 별칭을 줄 수 없다(하이버네이트는 가능하지만 가급적 사용을 추천하지 않는다.)
            // ex) select t From Team t join fetch t.members as m where m.username
            // fetch join을 연이어서 사용할 경우에만 별칭을 사용한다.


            // 객제지향 쿼리 언어2 - 중급 문법 -다형성 쿼리 -> select i from Item i where type(i) IN (Book, Movie)


            // 객제지향 쿼리 언어2 - 중급 문법 - 엔티티 직접 사용
            // 엔티티를 파라미터로 줄 수 있음
            // select m from Member m where m = :member 이후에 setParameter("member", member)


//            // 객제지향 쿼리 언어2 - 중급 문법 - Named 쿼리
//            // Member 클래스 위에 @NamedQuery를 추가한다. / 오류를 잡아내기 좋은 방법임.
//
//
//
//            Team teamA = new Team();
//            teamA.setName("teamA");
//            em.persist(teamA);
//
//            Team teamB = new Team();
//            teamB.setName("teamB");
//            em.persist(teamB);
//
//
//            Member member1 = new Member();
//            member1.setUsername("회원1");
//            member1.setAge(10);
//            member1.setTeam(teamA);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("회원2");
//            member2.setAge(10);
//
//            member2.setType(ADMIN);
//            member2.setTeam(teamB);
//            em.persist(member2);
//
//            Member member3 = new Member();
//            member3.setTeam(teamB);
//            em.persist(member3);
//
//            em.flush();
//            em.clear();
//
//            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", "회원1")
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member = " + member);
//            }


//          // 객제지향 쿼리 언어2 - 중급 문법 - 벌크 연산
            // 쿼리 한 번으로 여러 로우 변경(엔티티)

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(10);
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(10);

            member2.setType(ADMIN);
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setTeam(teamB);
            em.persist(member3);

//            em.flush();
//            em.clear();

            // 영향을 받은 카운트가 나옴
            int resultCount = em.createQuery("update Member m set m.age =20")
                    .executeUpdate();

//            em.clear();
            System.out.println("resultCount = " + resultCount);

            // 벌크 연산을 실행할 경우 주의해야 할 점
            // -> 벌크 연산을 아예 먼저 실행하던지, 아니면 벌크 연산 수행 후 영속성 컨텍스트를 초기화해야한다.
            // 예를 들어 위의 코드 상황에서 flush를 주석처리한다면 영속성 컨텍스트에 있는 객체의 나이는 변하지 않는다.

            System.out.println("member1.getAge() = " + member1.getAge());
            System.out.println("member2.getAge() = " + member2.getAge());
            System.out.println("member3.getAge() = " + member3.getAge());

            // 따라서 위처럼 em.clear()를 실행하여 영속성컨텍스트를 초기화 하는 것이 좋다
            tx.commit();
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
