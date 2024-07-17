package tobyspring.hellospring.learningtest

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class ClockTest {
    @Test
    fun clock() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val dt1 = LocalDateTime.now(clock)
        val dt2 = LocalDateTime.now(clock)

        Assertions.assertThat(dt1).isEqualTo(dt2)
    }
}
