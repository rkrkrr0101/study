package jpabook.jpashop.api;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.SimpleOrderQueryDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        return all;

    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDTO> ordersV2() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDTO> collect = all.stream()
                .map(o -> new SimpleOrderDTO(o))
                .collect(Collectors.toList());
        return collect;

    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDTO> ordersV3() {

        List<Order> all = orderRepository.findAllWithMemberDelivery();

        List<SimpleOrderDTO> collect = all.stream()
                .map(o -> new SimpleOrderDTO(o))
                .collect(Collectors.toList());
        return collect;

    }

    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderQueryDTO> ordersV4() {

        return orderRepository.findOrderDTO();


    }

    @Data
    private class SimpleOrderDTO {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDTO(Order o) {
            orderId = o.getId();
            name = o.getMember().getName();
            orderDate = o.getOrderDate();
            orderStatus = o.getStatus();
            address = o.getDelivery().getAddress();
        }
    }
}
