package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


// 방법 1
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Item {
//
//    @Id @GeneratedValue
//    private Long id;
//
//    public Item(Long id) {
//        this.id = id;
//    }

// 방법 2
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<Long> {

    @Id @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Item(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew(){
        return createdDate == null;
    }
}
