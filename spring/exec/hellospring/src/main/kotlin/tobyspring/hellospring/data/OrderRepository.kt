package tobyspring.hellospring.data

import jakarta.persistence.EntityManagerFactory
import tobyspring.hellospring.order.Order

class OrderRepository(private val emf: EntityManagerFactory) {
    fun save(order: Order) {
        val em = emf.createEntityManager()
        val transaction = em.transaction
        transaction.begin()

        try {
            em.persist(order)
            em.flush()
            transaction.commit()
        } catch (e: RuntimeException) {
            if (transaction.isActive) transaction.rollback()

            throw e
        } finally {
            if (em.isOpen) em.close()
        }
    }
}
