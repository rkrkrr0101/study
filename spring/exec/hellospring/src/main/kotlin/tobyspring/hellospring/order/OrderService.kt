package tobyspring.hellospring.order

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    fun createOrder(
        no: String,
        total: BigDecimal,
    ): Order {
        val order = Order(no, total)
        TransactionTemplate(transactionManager).execute {
            orderRepository.save(order)
        }

        return order
    }
}
