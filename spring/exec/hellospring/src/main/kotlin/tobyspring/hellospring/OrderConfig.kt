package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.orm.jpa.JpaTransactionManager
import tobyspring.hellospring.data.JpaOrderRepository
import tobyspring.hellospring.order.OrderService

@Configuration
@Import(DataConfig::class)
class OrderConfig {
    @Bean
    fun orderService(jpaTransactionManager: JpaTransactionManager): OrderService {
        return OrderService(orderRepository(), jpaTransactionManager)
    }

    @Bean
    fun orderRepository(): JpaOrderRepository {
        return JpaOrderRepository()
    }
}
