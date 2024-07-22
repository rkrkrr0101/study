package tobyspring.hellospring.order

interface OrderRepository {
    fun save(order: Order)
}
