package tobyspring.hellospring.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class Order(
    no: String,
    total: BigDecimal,
) {
    @Id
    @GeneratedValue
    var id = 0
        protected set

    @Column(unique = true)
    var no = no
        protected set
    var total = total
        protected set

    override fun toString(): String {
        return "Order(id=$id, no='$no', total=$total)"
    }
}
