package tobyspring.hellospring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.orm.jpa.JpaTransactionManager
import tobyspring.hellospring.order.OrderService
import java.math.BigDecimal

class OrderClient {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val beanFactory = AnnotationConfigApplicationContext(OrderConfig::class.java)
            val service = beanFactory.getBean(OrderService::class.java)
            val transactionManager = beanFactory.getBean(JpaTransactionManager::class.java)

            val order = service.createOrder("0100", BigDecimal(10))
            println("order = $order")
        }
    }
}
