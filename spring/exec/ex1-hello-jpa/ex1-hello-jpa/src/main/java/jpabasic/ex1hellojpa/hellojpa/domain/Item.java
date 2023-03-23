package jpabasic.ex1hellojpa.hellojpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private long id;
    private String name;
    private int price;
    private int stockQuantity;

}
