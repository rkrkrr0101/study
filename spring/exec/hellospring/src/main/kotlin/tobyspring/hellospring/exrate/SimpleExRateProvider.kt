package tobyspring.hellospring.exrate

import tobyspring.hellospring.payment.ExRateProvider
import java.math.BigDecimal

class SimpleExRateProvider : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        if (currency == "USD") return BigDecimal.valueOf(1000)
        throw IllegalArgumentException("지원되지않는 통화")
    }
}
