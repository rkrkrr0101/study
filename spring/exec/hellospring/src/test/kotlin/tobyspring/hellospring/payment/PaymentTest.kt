package tobyspring.hellospring.payment

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PaymentTest {
    @Test
    fun createPrepared() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val payment =
            Payment.createPrepared(
                1L,
                "USD",
                BigDecimal(10),
                BigDecimal(1000),
                LocalDateTime.now(clock),
            )

        Assertions.assertThat(payment.convertedAmount).isEqualByComparingTo(BigDecimal(10000))
        Assertions.assertThat(payment.validUntil).isEqualTo(LocalDateTime.now(clock).plusMinutes(30))
    }

    @Test
    fun isValid() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val payment =
            Payment.createPrepared(
                1L,
                "USD",
                BigDecimal(10),
                BigDecimal(1000),
                LocalDateTime.now(clock),
            )

        Assertions.assertThat(payment.isValid(clock)).isTrue()
    }
}
