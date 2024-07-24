package tobyspring.hellospring.order

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

class OrderServiceTxProxy(
    private val target: OrderService,
    private val transactionManager: PlatformTransactionManager,
) : OrderService {
    override fun createOrder(
        no: String,
        total: BigDecimal,
    ): Order {
        return target.createOrder(no, total)
    }

    override fun createOrders(reqs: List<OrderReq>): List<Order> {
        return TransactionTemplate(transactionManager).execute { target.createOrders(reqs) }!!
    }
}
