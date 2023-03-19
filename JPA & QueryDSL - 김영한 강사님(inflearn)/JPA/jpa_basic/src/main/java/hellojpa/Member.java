//package hellojpa;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
////enum, final, inner, intereface 사용하면 안됨
//@Entity() //(name = "다른 엔티티 이름 사용 가능")
////@Table(name = "다른 테이블 이름 사용가능")
//public class Member {
//
//    @Id
//    private Long id;
//
//    @Column(unique = true, length = 10)
//    private String name;
//
//    //ENTITY는 무조건 기본생성자가 존재하여야 한다.
//    public Member(){}
//
//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}

package hellojpa;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// @Table => 여기에서 unique 제약조건을 주는 것이 바람직하다
//@SequenceGenerator(name = "member_seq_generator",
//sequenceName = "member_seq")
//@TableGenejrator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)


@Entity
public class Member extends BaseEntity{
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator") //SEQUENCE(ORACLE),
//    @GeneratedValue(strategy = GenerationType.TABLE,
//    generator = "MEMBER_SEQ_GENERATOR")


/*
*  권장하는 식별자 전략략
*  1. 기본 키 제약 조건 : null아님, 유일해야 한다, 변하면 안된다(변하면 안된다를 구현하는게 제일 까다로움)
*  2. 미래까지 (변하지 않는) 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키) 사용하자.
*  3. 주민등록번호 조차도 기본 키로 적절하지 않다.
*
*  권장 : Long형 + 대체키 + 키 생성전략 사용* -> auto_increment or sequence object를 사용하거나 유효아이디나 회사내의 랜덤값을 사용
* */


    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; // id에는 Long을 사용하는 것이 바람직하다.

    @Column//, nullable = true)// columnDefinition = "varchar(100) default 'EMPTY'"
    private String username;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    //실무에서는 LAZY만 사용해야한다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Member(){}
//    @Column(name = "team_id")
//    private Long temaId;

//    @ManyToOne//(fetch = FetchType.LAZY) // 지연로딩전략
//    @JoinColumn(name = "team_id") // 외래키가 있는 곳을 연관관계의 주인으로 정한다.
//    private Team team;

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team){
        this.team = team;
    }
//
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

//    public Long getTemaId() {
//        return temaId;
//    }
//
//    public void setTemaId(Long temaId) {
//        this.temaId = temaId;
//    }

    //    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    //    private int age; // Integer로 해도 됨
//
//    @Enumerated(EnumType.STRING) // EnumType.ORDINAL은 가급적 사용하지 않는다. 해결할 수 없는 버그가 생길지도...
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate test1;
//    private LocalDateTime test2;
//
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
//
//    @Lob
//    private String description;
//
//    @Transient
//    private int temp;
//

//    public Long getId() {
//        return id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public Date getLastModifiedDate() {
//        return lastModifiedDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getTemp() {
//        return temp;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public void setLastModifiedDate(Date lastModifiedDate) {
//        this.lastModifiedDate = lastModifiedDate;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setTemp(int temp) {
//        this.temp = temp;
//    }
//Getter, Setter…
}
