package tobyspring.hellospring.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) : OrderService {
    override fun createOrder(
        no: String,
        total: BigDecimal,
    ): Order {
        val order = Order(no, total)

        orderRepository.save(order)

        return order
    }

    @Transactional
    override fun createOrders(reqs: List<OrderReq>): List<Order> {
        return reqs.stream().map {
            createOrder(it.no, it.total)
        }.toList()
    }
}
