package tobyspring.hellospring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import tobyspring.hellospring.data.OrderRepository
import tobyspring.hellospring.order.Order
import java.math.BigDecimal

class DataClient {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
            val repository = beanFactory.getBean(OrderRepository::class.java)

            val order = Order("100", BigDecimal(10))
            repository.save(order)
            println(order)
            val order2 = Order("100", BigDecimal(1))
            repository.save(order2)
            println(order2)
        }
    }
}
