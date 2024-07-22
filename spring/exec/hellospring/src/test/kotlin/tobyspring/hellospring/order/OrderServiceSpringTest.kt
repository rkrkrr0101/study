package tobyspring.hellospring.order

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import tobyspring.hellospring.OrderConfig
import java.math.BigDecimal

@ContextConfiguration(classes = [OrderConfig::class])
@ExtendWith(SpringExtension::class)
class OrderServiceSpringTest(
    @Autowired val orderService: OrderService,
) {
    @Test
    fun createOrder() {
        val order = orderService.createOrder("0100", BigDecimal(1))

        Assertions.assertThat(order.id).isGreaterThan(0)
    }
}
