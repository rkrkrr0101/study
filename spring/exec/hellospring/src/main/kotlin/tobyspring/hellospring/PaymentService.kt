package tobyspring.hellospring

import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class PaymentService(private val exRateProvider: ExRateProvider) {
    fun prepare(
        orderId: Long,
        currency: String,
        foreignCurrencyAmount: BigDecimal,
    ): Payment {
        val exRate = exRateProvider.getExRate(currency)
        val convertedAmount = foreignCurrencyAmount * exRate
        val validUntil = LocalDateTime.now().plusMinutes(30)

        return Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil)
    }
}
