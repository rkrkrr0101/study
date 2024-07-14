package tobyspring.hellospring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

class Client {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val beanFactory = AnnotationConfigApplicationContext(ObjectFactory::class.java)
            val paymentService = beanFactory.getBean(PaymentService::class.java)
            val prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7))
            println(prepare)
        }
    }
}
