package hello.springtx.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public void order(Order order) throws NotEnoughMoneyException {
        log.info("order호출");
        orderRepository.save(order);
        log.info("결제");
        if (order.getUsername().equals("예외")){
            log.info("시스템예외발생");
            throw new RuntimeException("시스템예외");
        } else if (order.getUsername().equals("잔고부족")) {
            log.info("잔고부족 비즈니스예외");
            order.setPayStatus("대기");
            throw new NotEnoughMoneyException("잔고부족");

        }else{
            log.info("정상승인");
            order.setPayStatus("완료");
        }

    }
}
