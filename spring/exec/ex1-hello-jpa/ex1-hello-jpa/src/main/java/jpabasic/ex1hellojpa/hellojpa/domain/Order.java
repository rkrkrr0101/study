package jpabasic.ex1hellojpa.hellojpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;
    @Column(name="MEMBER_ID")
    private Long memberId;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
