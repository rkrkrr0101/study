package tobyspring.hellospring

import java.math.BigDecimal

interface ExRateProvider {
    fun getExRate(currency: String): BigDecimal
}
