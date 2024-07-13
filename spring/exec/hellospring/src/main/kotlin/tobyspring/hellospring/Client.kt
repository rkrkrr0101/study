package tobyspring.hellospring

import java.math.BigDecimal

class Client {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val paymentService = PaymentService()
            val prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7))
            println(prepare)
        }
    }
}
