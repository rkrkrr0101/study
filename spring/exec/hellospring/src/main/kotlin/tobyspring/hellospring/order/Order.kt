package tobyspring.hellospring.order

import java.math.BigDecimal

class Order(
    no: String,
    total: BigDecimal,
) {
    var id = 0
        protected set

    var no = no
        protected set
    var total = total
        protected set

    override fun toString(): String {
        return "Order(id=$id, no='$no', total=$total)"
    }
}
