package tobyspring.hellospring.payment

import java.math.BigDecimal

interface ExRateProvider {
    fun getExRate(currency: String): BigDecimal
}
