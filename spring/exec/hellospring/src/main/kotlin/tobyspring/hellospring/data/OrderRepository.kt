package tobyspring.hellospring.data

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import tobyspring.hellospring.order.Order

class OrderRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun save(order: Order) {
        entityManager.persist(order)
    }
}
