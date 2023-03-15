package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemService itemService;

    @Test
    public void 상품주문() throws Exception {
        //g
        Member member = createMember();
        Book book = createBook();
        //w
        Long orderId = orderService.order(member.getId(), book.getId(), 2);
        //t
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * 2);
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(8);

    }


    @Test
    public void 상품재고수량초과() throws Exception {
        //g
        Member member = createMember();
        Item book = createBook();
        int orderCount = 11;
        //w

        //t
        org.junit.jupiter.api.Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), book.getId(), orderCount);
        });
    }

    @Test
    public void 주문취소() throws Exception {
        //g
        Member member = createMember();
        Item book = createBook();
        int orderCount = 8;
        //w
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        orderService.cancelOrder(orderId);
        //t
        Order order = orderRepository.findOne(orderId);
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(10);
        Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    public void 재고수량초과() throws Exception {
        //g
        //w
        //t
    }

    private Book createBook() {
        Book book = new Book();
        book.setName("jpaqqq");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}