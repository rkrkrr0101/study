package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import tobyspring.hellospring.data.JdbcOrderRepository
import tobyspring.hellospring.order.OrderRepository
import tobyspring.hellospring.order.OrderService
import tobyspring.hellospring.order.OrderServiceImpl
import javax.sql.DataSource

@Configuration
@Import(DataConfig::class)
@EnableTransactionManagement
class OrderConfig {
    @Bean
    fun orderService(
        orderRepository: OrderRepository,
        transactionManager: PlatformTransactionManager,
    ): OrderService {
        return OrderServiceImpl(orderRepository)
    }

    @Bean
    fun orderRepository(dataSource: DataSource): OrderRepository {
        return JdbcOrderRepository(dataSource)
    }
}
