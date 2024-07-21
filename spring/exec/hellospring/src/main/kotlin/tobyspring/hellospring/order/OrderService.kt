package tobyspring.hellospring.order

import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import tobyspring.hellospring.data.OrderRepository
import java.math.BigDecimal

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val transactionManager: JpaTransactionManager,
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
