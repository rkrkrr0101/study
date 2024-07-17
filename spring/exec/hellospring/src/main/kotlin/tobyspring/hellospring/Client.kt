package tobyspring.hellospring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import tobyspring.hellospring.payment.PaymentService
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class Client {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val beanFactory = AnnotationConfigApplicationContext(PaymentConfig::class.java)
            val paymentService = beanFactory.getBean(PaymentService::class.java)

            val prepare1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7))
            println(prepare1)
            println("------------------")
            TimeUnit.SECONDS.sleep(1)
            val prepare2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7))
            println(prepare2)

            TimeUnit.SECONDS.sleep(4)

            val prepare3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7))
            println(prepare3)
        }
    }
}
