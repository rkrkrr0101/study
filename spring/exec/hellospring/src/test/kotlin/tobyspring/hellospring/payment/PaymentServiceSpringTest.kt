@file:Suppress("ktlint:standard:no-wildcard-imports")

package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import tobyspring.hellospring.TestPaymentConfig
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

@ContextConfiguration(classes = [TestPaymentConfig::class])
@ExtendWith(SpringExtension::class)
class PaymentServiceSpringTest {
    @Autowired
    lateinit var paymentService: PaymentService

    @Autowired
    lateinit var clock: Clock

    @Test
    @DisplayName("prepare메서드 요구사항 검증")
    fun prepare() {
        val payment = paymentService.prepare(100, "USD", BigDecimal(10))

        Assertions.assertThat(payment.exRate).isEqualByComparingTo(BigDecimal(1000))
        Assertions.assertThat(payment.convertedAmount).isEqualTo(BigDecimal(10000))
    }

    @Test
    fun validUntil() {
        val payment = paymentService.prepare(1L, "USD", BigDecimal(10))

        val now = LocalDateTime.now(clock)
        val expectedValidUntil = now.plusMinutes(30)

        Assertions.assertThat(payment.validUntil).isEqualTo(expectedValidUntil)
    }
}
