package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
//        for (int i = 0; i < 1000; i++) {
//            initService.dbInit1();
//        }
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);


            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);


        }

        public void dbInit2() {
            Member member = createMember("userB", "부산", "2", "2222");
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);
            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit3() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);


            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book2);
            Book book3 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book3);
            Book book4 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book4);
            Book book5 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book5);
            Book book6 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book6);
            Book book7 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book7);
            Book book8 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book8);
            Book book9 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book9);


            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            OrderItem orderItem3 = OrderItem.createOrderItem(book3, 10000, 1);
            OrderItem orderItem4 = OrderItem.createOrderItem(book4, 10000, 1);
            OrderItem orderItem5 = OrderItem.createOrderItem(book5, 10000, 1);
            OrderItem orderItem6 = OrderItem.createOrderItem(book6, 10000, 1);
            OrderItem orderItem7 = OrderItem.createOrderItem(book7, 10000, 1);
            OrderItem orderItem8 = OrderItem.createOrderItem(book8, 10000, 1);
            OrderItem orderItem9 = OrderItem.createOrderItem(book9, 10000, 1);
            OrderItem orderItem10 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem11 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem12 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem13 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem14 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem15 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem16 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem17 = OrderItem.createOrderItem(book1, 10000, 1);


            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2, orderItem3, orderItem4, orderItem5, orderItem6, orderItem7, orderItem8, orderItem9, orderItem10, orderItem11, orderItem12, orderItem13, orderItem14, orderItem15, orderItem16, orderItem17);
            em.persist(order);


        }

        private static Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private static Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }


    }
}


