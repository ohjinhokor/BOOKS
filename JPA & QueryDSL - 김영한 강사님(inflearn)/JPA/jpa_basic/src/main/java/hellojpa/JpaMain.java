package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

    try{
//        Member member = new Member();
//        member.setId(2L);
//        member.setName("HelloB");
//        em.persist(member);
//        Member findMember = em.find(Member.class, 1L);
//        System.out.println("findMember.getId() = " + findMember.getId());
//        System.out.println("findMember.getName() = " + findMember.getName());
//
////        em.remove(findMember);
//        findMember.setName("helloJPA");
//
//        tx.commit();
//
//        List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                .setFirstResult(5)
//                .setMaxResults(8)
//                .getResultList();
//
//        for (Member member : result) {
//            System.out.println("member.getName() = " + member.getName());
//        }

        //비영속
//        Member member = new Member();
//        member.setId(101L);
//        member.setName("HelloJPAAA");
//
//        //영속
//        System.out.println("=== BEFORE ===");
//        em.persist(member);
//        System.out.println("=== AFTER ===");

//        Member findMember1 = em.find(Member.class, 101L);
//        Member findMember2 = em.find(Member.class, 101L);
//        // 이처럼 조회를 2번할 시에는, 1번은 1차캐시에서 데이터를 가지고 온다.
//
//        System.out.println(findMember1 == findMember2);
//        //영속성 콘텍스트
//
//        Member member1 = new Member(150L, "A");
//        Member member2 = new Member(151L, "B");
//
//        em.persist(member1);
//        em.persist(member2);
////        System.out.println("findMember.id = " + findMember.getId());
////        System.out.println("findMember.getName() = " + findMember.getName());

//        Member member = em.find(Member.class, 150L);
//        member.setName("ABCD");
//
//        //  em.persist(member) 할 필요 없음
//
//        System.out.println("====================");
//        Member member = new Member(200L, "member200");
//        em.persist(member);
//
//        em.flush();
//
//        System.out.println("=============아래 확인========================");
//        Member member = em.find(Member.class, 150L);
//        member.setName("ABCD");
//        System.out.println("=========================위에 확인======================");
//        //em.detach(member);
//        //em.clear();
//        //em.close();
//
//        System.out.println("====================");
//
//        Member member = new Member();
//        //member.setId("ID_A");
//        member.setUsername("B");
//        //member.setRoleType(RoleType.USER);
//
//        em.persist(member);
//
//        System.out.println("===============================");

        // 저장


//        Team team = new Team();
//        team.setName("TeamA");
//        //team.getMembers().add(member);
//        em.persist(team);
//
//        Member member = new Member();
//        member.setUsername("member1");
////        member.changeTeam(team);
//        em.persist(member);
//
////        team.getMembers().add(member);  -->> 이를 연관관계 편의 메서드로 바꿔주자
//
//        em.flush();
//        em.clear();
//

//        // 찾기
//        Member findmember = em.find(Member.class, member.getId());
//        Team findTeam = findmember.getTeam();
//        List<Member> members = findTeam.getMembers();
//
//        System.out.println("===============================================");
//        for (Member m : members) {
//            System.out.println("m.getUsername() = " + m.getUsername());
//        }
//
//        Movie movie = new Movie();
//        movie.setActor("영화배우");
//        movie.setDirector("감독");
//        movie.setPrice(10000);
//        movie.setName("영화제목");
//        em.persist(movie);

//        Movie findMovie = em.find(Movie.class, movie.getId());


//        Member member = new Member();
//        member.setUsername("user");
//        member.setCreatedBy("kim");
//        member.setCreatedDate(LocalDateTime.now());
//
//        em.persist(member);
//
//        em.flush();
//        em.clear();

//        System.out.println("---------------------------------------------");
//        System.out.println("findMovie = " + findMovie);;
//        Book book = new Book();
//        em.persist(book);
//

        //고급 매핑
//        Movie movie2 = new Movie();
//        movie2.setActor("영화배우2");
//        movie2.setDirector("감독2");
//        movie2.setPrice(100002);
//        movie2.setName("영화제목2");
//
//        em.persist(movie2);
//
//        Book book = new Book();
//        book.setName("책 제목");
//        book.setPrice(100000);
//        book.setIsbn("ISBN");
//        book.setAuthor("책 저자");
//
//        em.persist(book);
//
//        //MappedSuperClass의 함수와 변수 사용 예시
//        Member member = new Member();
//        member.setUsername("유저 이름");
//        member.setCreatedDate(LocalDateTime.now());
//
//        em.flush();
//        em.clear();


        //프록시와 연관관계 관리 - 프록시
//        Member member1 = new Member();
//        member1.setUsername("멤버 이름");
//
//        em.persist(member1);
//        em.flush();
//        em.clear();
//
//
////        Member member = em.find(Member.class, 1L);
//
//        // select 쿼리를 날리지 않고 member객체를 받아옴, 가짜 엔티티라고 생각하면 됨.(프록시)
//        Member member = em.getReference(Member.class, member1.getId());
//
//
//        //이 때 쿼리를 날림. 기존에 객체만 가져올 때는 쿼리를 날리지 않다가 username처럼 db를 이용해서 알 수 있는 데이터가 필요할 때 쿼리를 날림
//        System.out.println("select 쿼리 시작");
//        System.out.println("findmember = " + member.getUsername());
//        // 쿼리를 날린 이후 프록시는 엔티티를 targeting한다.
//
//
////        printMemberAndTeam(member);
//
//        printMember(member);

////         프록시와 연관관계 관리 - 즉시 로딩과 지연 로딩
////        (fetchType.Lazy)
//        Team team = new Team();
//        team.setName("teamA");
//        em.persist(team);
//
//        Member member1 = new Member();
//        member1.setUsername("member1");
//        member1.setTeam(team);
//        em.persist(member1);
//
//
//        em.flush();
//        em.clear();
//
//        Member m = em.find(Member.class, member1.getId());
//        System.out.println("여기까지는 member만 select함 team은 프록시임");
//
//        // 여기서 team에 select 쿼리를 날림
//        m.getTeam().getName();
//
//
//        // 이런식으로 페치 조인을 사용하면 team까지 한 번에 select 할 수 있다
//        List<Member> resultList = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                .getResultList();
//
//        tx.commit();


//        //(fetchType.EAGER) 그러나 실무에서 즉시 로딩은 사용하지 않는다.
//        // 실무에서는 fetchType.LAZY 즉 지연 로딩만 사용한다.
//        // 즉시로딩 사용하지 않는 이유
//        // 1. 성능 저하
//        // 2. N+1 문제를 일으킴
//
//        Team team = new Team();
//        team.setName("teamA");
//        em.persist(team);
//
//        Member member1 = new Member();
//        member1.setUsername("member1");
//        member1.setTeam(team);
//        em.persist(member1);
//
//
//        em.flush();
//        em.clear();
//
//        // 한 번에 member와 team을 다 select 함
//        Member m = em.find(Member.class, member1.getId());
//        System.out.println("Team과 Member를 한 번에 select함");
//        m.getTeam().getName();
//
//        tx.commit();


//        // 프록시와 연관관계 관리 - 영속성전이(CASCADE)
//        // 즉시 로딩, 지연 로딩, 연관관계 세팅과 전혀 관계 없음 - 헷갈리기 쉬움
//
//        Child child1 = new Child();
//        Child child2 = new Child();
//
//        Parent parent = new Parent();
//        parent.addChild(child1);
//        parent.addChild(child2);
//
//        // persist를 3번 하게됨.
//        // parent를 persist하면 child도 한 번에 persist되도록 하기 위해 영속성전이를 사용함
//        // 그러나 parent 클래스의 childList에 CasecadeType.ALL이나 CasecadeType.PERSIST를 추가하면 child까지 한 번에 persist됨
//        em.persist(parent);
//        em.persist(child1);
//        em.persist(child2);
//
//
//        // 프록시와 연관관계 관리 - 고아 객체
//        // 부모 클래스에 orphanRemoval = true로 해두면 자식 객체와 부모객체의 관계가 끊어졌을 때 자식 객체를 데이터베이스에서 자동으로  삭제함
//        em.flush();
//        em.clear();
//
//        Parent findParent = em.find(Parent.class, parent.getId());
//        findParent.getChildList().remove(0); // 자식 객체가 데이터베이스에서 삭제됨
//
//        em.remove(parent); // parent와 관계있는 자식 객체가 모두 지워짐
//
//
//        tx.commit();

//        //값 타입 - 임베디드 타입
//        // 클래스 타입을 필드 값으로 가질 수 있음
//        Member2 member = new Member2();
//        member.setUsername("유저 이름");
//        member.setHomeAddress(new Address("city1", "street2", "zipcode3"));
//        member.setWorkPeriod(new Period());
//
//        em.persist(member);

        //값 타입 - 값 타입과 불변 객체 -> 하면 안되는 방법
//        Address address = new Address("city1", "street2", "zipcode3");
//
//        Member2 member1 = new Member2();
//        member1.setUsername("member1");
//        member1.setHomeAddress(address);
//        em.persist(member1);
//
//        Member2 member2 = new Member2();
//        member2.setUsername("member2");
//        member2.setHomeAddress(address);
//        em.persist(member2);
//
//        // member1의 city만 바꿨다고 생각했는데 member2의 city까지 바뀌는 결과가 나타남
//        // 같은 객체를 참조하기 때문이다.
//        member1.getHomeAddress().setCity("newcity");


//        //값 타입 - 값 타입과 불변 객체 -> 올바른 방법
//        Address address = new Address("city1", "street2", "zipcode3");
//
//        Member2 member1 = new Member2();
//        member1.setUsername("member1");
//        member1.setHomeAddress(address);
//        em.persist(member1);
//
//        Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//        Member2 member2 = new Member2();
//        member2.setUsername("member2");
//        member2.setHomeAddress(copyAddress);
//        em.persist(member2);
//
//        // 이렇게 하면 공유참조를 막을 수 있지만 실수가 나올 확률이 너무 크다 -> 문제의 해결이 필요함(불변 객체를 사용함)
//        // 불변 객체를 사용하기 위해서 생성 시에(생성자에) 값을 할당하게 하고, set함수를 만들지 않으면 된다.
////        member1.getHomeAddress().setCity("newCity");
//
//
//        // set함수를 private했기 때문에 객체안의 필드 값을 바꾸고 싶다면 객체를 완전히 새로 만들어야 함
//        Address newAddress = new Address("Newcity", address.getStreet(), address.getZipcode());
//        member1.setHomeAddress(newAddress);


////        // 값 타입 - 값 타입의 비교
////        System.out.println("\n address.equals(copyAddress) : "+address.equals(copyAddress));
//
//        //값 타입 - 값 타입 컬렉션
//        Member2 member = new Member2();
//        member.setUsername("member1");
//        member.setHomeAddress(new Address("city1", "street2", "zipcode3"));
//
//        member.getFavoriteFoods().add("치킨");
//        member.getFavoriteFoods().add("족발");
//        member.getFavoriteFoods().add("피자");
//
//        member.getAddressHistory().add(new AddressEntity(new Address("oldCity", "oldStreet", "oldZipcode")));
//        member.getAddressHistory().add(new AddressEntity(new Address("oldCity2", "oldStreet2", "oldZipcode2")));
//
//        em.persist(member);
//
//        em.flush();
//        em.clear();
//
//        System.out.println("=======find Start=========");
//        // 값 타입 컬렉셕은 모두 지연로딩임을 로그에 나오는 쿼리를 통해 알 수 있음
//        Member2 findMember = em.find(Member2.class, member.getId());
//
//        //만약 findMember가 컬렉션을 사용한다면 그 때 쿼리를 발생시킴
////        System.out.println("==========getAddressHistory()===========");
////        List<Address> addressHistory = findMember.getAddressHistory();
////        for (Address address : addressHistory) {
////            System.out.println("address = " + address.getCity());
////        }
////        Set<String> favoriteFoods = findMember.getFavoriteFoods();
////        for (String favoriteFood : favoriteFoods) {
////            System.out.println("favoriteFood = "+favoriteFood);
////        }
//
//
//        // 값 타입 컬렉션 수정
//        // 값 타입을 변경하는 잘못된 예시 : findMember.getHomeAddress().setCity("newCity")
//        // 값 타입을 변경할 때는 객체를 완전히 새로 넣어야 함! 그래야 참조 공유 문제가 생기지 않음
//        Address a = findMember.getHomeAddress();
//        findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//        //set안에 있는 값 변경
//        findMember.getFavoriteFoods().remove("치킨");
//        findMember.getFavoriteFoods().add("한식");
//
//        // 이 때 Address가 지워지기 위해서는 Address간의 비교가 가능해야 한다. 따라서 equals 함수가 잘 구현되어 있어야 한다.
//        //이 부분의 sql을 보면 예상과는 다른 부분이 있다.
//        // 컬럼 하나만 지우고 하나를 추가할 것 같지만, 전체를 다 지우고 전체를 다시 추가하는 방식을 택한다.
//        // 결국 실무에서는     @ElementCollection, @CollectionTable를 거의 사용하지 않는다.
//        // !!!!!!!!!! 아래 한 문장이 중요!!!!!!!!!!!!
//        // 실무에서는 값 타입 컬렉션 대신에 일대다 관계를 이용하여 문제를 해결한다
//        findMember.getAddressHistory().remove(new AddressEntity(new Address("city1", "street2", "zipcode3")));
//        findMember.getAddressHistory().add(new AddressEntity(new Address("newCity1", "newStreet2", "newZipcode3")));

        //객체 지향 쿼리 언어1 - 기본문법 - 소개
        // 테이블이 아닌 엔티티 객체를 대상으로 sql을 날린다는 점에서 차이가 있다.
        // JPQL을 한 마디로 정의한다면 객체 지향 sql이다.
//        List<Member2> resultList = em.createQuery("select m FROM Member2 m where m.username like '%kim%'", Member2.class)
//                .getResultList();
//
//        for (Member2 member : resultList) {
//            System.out.println("member = "+member);
//        }

//        // JPQL의 동적쿼리를 보여주기 위한 코드(실무에서 사용 잘 안함 -> 대신 QueryDsl을 사용함)
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Member2> query = criteriaBuilder.createQuery(Member2.class);
//
//        Root<Member2> m = query.from(Member2.class);
//
//        CriteriaQuery<Member2> cq = query.select(m).where(criteriaBuilder.equal(m.get("username"), "kim"));
//
//        List<Member2> resultList = em.createQuery(cq)
//                .getResultList();


        // 네이티브 sql(JPA가 제공하는 sql을 직접 사용(특정 데이터베이스에 의존적임)
        try{
            em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER")
                    .getResultList();
            tx.commit();
        }
        catch(Exception e){

        }

    } catch(Exception e) {
        System.out.println("여기로");
        tx.rollback();
        }
    finally{
            em.close();
        }
        emf.close();
    }


//    private static void printMember(Member member) {
//        System.out.println("member = " + member.getUsername());
//    }
//
//    private static void printMemberAndTeam(Member member){
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
////        System.out.println("team = " + team.getName());
//
//
//    }
}
