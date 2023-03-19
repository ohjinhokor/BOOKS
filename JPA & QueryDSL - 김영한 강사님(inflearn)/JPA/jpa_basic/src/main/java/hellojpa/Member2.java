package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member2 extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //기간
    @Embedded // @Embeddable이 적혀있는 클래스를 필드로 쓸 수 있게함
    private Period workPeriod;

    //주소
    @Embedded // @Embeddable이 적혀있는 클래스를 필드로 쓸 수 있게함
    private Address homeAddress;

    // 값 타입 - 값 타입 컬렉션

    @ElementCollection // 컬렉션 테이블과 매핑하기 위해 사용하는 어노테이션
    @CollectionTable(name = "FAVORITE_FOODS", joinColumns =
        @JoinColumn(name = "MEMBER_ID") // 테이블 명과, 현재 테이블에서 어떤 값을 FK로 잡을 것인지를 정하는 것
    )
    @Column(name = "FOOD_NAME") // 테이블 명이 아니라 column명이 FOOD_NAME임을 주의하자!
    private Set<String> favoriteFoods = new HashSet<>();

    // 이 부분을 일대다 관계로 풀어냄
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns =
//        @JoinColumn(name="MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();


    //값 타입 컬렉션을 대체할 때는 일대다 단방향 매핑으로 만들어도 괜찮음
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

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

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
