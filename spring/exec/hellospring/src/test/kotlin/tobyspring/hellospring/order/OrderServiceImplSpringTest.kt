package tobyspring.hellospring.order

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import tobyspring.hellospring.OrderConfig
import java.math.BigDecimal
import javax.sql.DataSource

@ContextConfiguration(classes = [OrderConfig::class])
@ExtendWith(SpringExtension::class)
class OrderServiceImplSpringTest(
    @Autowired val orderService: OrderService,
    @Autowired val dataSource: DataSource,
) {
    @Test
    fun createOrder() {
        val order = orderService.createOrder("0100", BigDecimal(1))

        Assertions.assertThat(order.id).isGreaterThan(0)
    }

    @Test
    fun createOrders() {
        val orderReqs =
            listOf(
                OrderReq("0200", BigDecimal(1)),
                OrderReq("0201", BigDecimal(2)),
            )
        val orders = orderService.createOrders(orderReqs)

        Assertions.assertThat(orders).hasSize(2)
        orders.forEach {
            Assertions.assertThat(it.id).isGreaterThan(0)
        }
    }

    @Test
    fun createDuplicatedOrders() {
        val orderReqs =
            listOf(
                OrderReq("0300", BigDecimal(1)),
                OrderReq("0300", BigDecimal(2)),
            )
        Assertions.assertThatThrownBy {
            orderService.createOrders(orderReqs)
        }.isInstanceOf(DuplicateKeyException::class.java)
        val client = JdbcClient.create(dataSource)
        val count = client.sql("select count(*) from orders where no='0300'").query().singleValue()
        Assertions.assertThat(count).isEqualTo(0L)
    }
}
