@file:Suppress("ktlint:standard:no-wildcard-imports")

package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PaymentServiceTest {
    private val clock: Clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

    @Test
    @DisplayName("prepare메서드 요구사항 검증")
    fun prepare() {
        testAmount(BigDecimal(500), BigDecimal(5000), clock)
        testAmount(BigDecimal(1000), BigDecimal(10000), clock)
        testAmount(BigDecimal(3000), BigDecimal(30000), clock)

        // Assertions.assertThat(testAmount.validUntil).isAfter(LocalDateTime.now())
        // Assertions.assertThat(testAmount.validUntil).isBefore(LocalDateTime.now().plusMinutes(30))
    }

    @Test
    fun validUntil() {
        val paymentService = PaymentService(ExRateProviderStub(BigDecimal(1000)), clock)

        val payment = paymentService.prepare(1L, "USD", BigDecimal(10))

        val now = LocalDateTime.now(clock)
        val expectedValidUntil = now.plusMinutes(30)

        Assertions.assertThat(payment.validUntil).isEqualTo(expectedValidUntil)
    }

    private fun testAmount(
        exRate: BigDecimal,
        convertedAmount: BigDecimal,
        clock: Clock,
    ) {
        val provider = ExRateProviderStub(exRate)
        val paymentService = PaymentService(provider, clock)

        val payment = paymentService.prepare(100, "USD", BigDecimal(10))

        Assertions.assertThat(payment.exRate).isEqualByComparingTo(exRate)
        Assertions.assertThat(payment.convertedAmount).isEqualTo(convertedAmount)
    }
}
