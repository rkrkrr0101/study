package tobyspring.hellospring.exrate

import tobyspring.hellospring.payment.ExRateProvider
import java.math.BigDecimal
import java.time.LocalDateTime

class CachedExRateProvider(private val target: ExRateProvider) : ExRateProvider {
    private var cachedExRate: BigDecimal = target.getExRate("USD")
    private var cacheExpTime: LocalDateTime = LocalDateTime.now().plusSeconds(3)

    override fun getExRate(currency: String): BigDecimal {
        if (cacheExpTime.isBefore(LocalDateTime.now())) {
            cachedExRate = target.getExRate("USD")
            cacheExpTime = LocalDateTime.now().plusSeconds(3)
        }
        return cachedExRate
    }
}
