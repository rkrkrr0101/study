package tobyspring.hellospring.data

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import tobyspring.hellospring.order.Order
import tobyspring.hellospring.order.OrderRepository

class JpaOrderRepository : OrderRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun save(order: Order) {
        entityManager.persist(order)
    }
}
