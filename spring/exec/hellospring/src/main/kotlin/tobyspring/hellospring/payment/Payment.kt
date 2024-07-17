package tobyspring.hellospring.payment

import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

class Payment(
    val orderId: Long,
    val currency: String,
    val foreignCurrencyAmount: BigDecimal,
    val exRate: BigDecimal,
    val convertedAmount: BigDecimal,
    val validUntil: LocalDateTime,
) {
    companion object {
        fun createPrepared(
            orderId: Long,
            currency: String,
            foreignCurrencyAmount: BigDecimal,
            exRate: BigDecimal,
            now: LocalDateTime,
        ): Payment {
            val convertedAmount = foreignCurrencyAmount * exRate
            val validUntil = now.plusMinutes(30)
            return Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil)
        }
    }

    fun isValid(clock: Clock): Boolean {
        return LocalDateTime.now().isBefore(this.validUntil)
    }

    override fun toString(): String {
        return "Payment(orderId=$orderId, currency='$currency', foreignCurrencyAmount=$foreignCurrencyAmount, exRate=$exRate," +
            " convertedAmount=$convertedAmount, validUntil=$validUntil)"
    }
}
