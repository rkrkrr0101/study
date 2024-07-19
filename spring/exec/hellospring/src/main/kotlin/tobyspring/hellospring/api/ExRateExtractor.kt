package tobyspring.hellospring.api

import java.math.BigDecimal

fun interface ExRateExtractor {
    fun extract(response: String): BigDecimal
}
