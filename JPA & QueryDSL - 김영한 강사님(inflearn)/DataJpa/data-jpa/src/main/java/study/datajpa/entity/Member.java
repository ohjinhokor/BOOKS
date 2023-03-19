package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"}) // team처럼 다른 entity는 나오게 하지 않아야 한다. 잘못하면 무한루프가 될 수도..
// JPA NamedQuery(실무에서 자주 사용하지는 않음)
@NamedQuery(
        name="Member.findByUsername",
        query="select m from Member m where m.username = :username"
)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // 엔티티의 기본생성자는 private이 아니라 protected로 열어놔야 함

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }


    public void changeUsername(String username){
        this.username = username;
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

}
