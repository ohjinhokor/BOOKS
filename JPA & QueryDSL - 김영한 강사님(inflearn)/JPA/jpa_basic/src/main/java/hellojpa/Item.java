package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 테이블 여러개, 가장 많이 쓰이는 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 부모 클래스 없이 자식 클래스만. 왠만해서는 사용하지 않는 전략
@DiscriminatorColumn
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
