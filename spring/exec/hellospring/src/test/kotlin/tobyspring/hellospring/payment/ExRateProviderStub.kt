package tobyspring.hellospring.payment

import java.math.BigDecimal

class ExRateProviderStub(private var exRate: BigDecimal) : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        return exRate
    }

    fun setExRate(changeExRate: BigDecimal) {
        exRate = changeExRate
    }
}
