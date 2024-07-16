@file:Suppress("ktlint:standard:no-wildcard-imports")

package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PaymentServiceTest {
    @Test
    @DisplayName("prepare메서드 요구사항 검증")
    fun prepare() {
        testAmount(BigDecimal(500), BigDecimal(5000))
        testAmount(BigDecimal(1000), BigDecimal(10000))
        testAmount(BigDecimal(3000), BigDecimal(30000))

        // Assertions.assertThat(testAmount.validUntil).isAfter(LocalDateTime.now())
        // Assertions.assertThat(testAmount.validUntil).isBefore(LocalDateTime.now().plusMinutes(30))
    }

    private fun testAmount(
        exRate: BigDecimal,
        convertedAmount: BigDecimal,
    ) {
        val provider = ExRateProviderStub(exRate)
        val paymentService = PaymentService(provider)

        val payment = paymentService.prepare(100, "USD", BigDecimal(10))

        Assertions.assertThat(payment.exRate).isEqualByComparingTo(exRate)
        Assertions.assertThat(payment.convertedAmount).isEqualTo(convertedAmount)
    }
}
