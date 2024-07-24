package tobyspring.hellospring.data

import jakarta.annotation.PostConstruct
import org.springframework.jdbc.core.simple.JdbcClient
import tobyspring.hellospring.order.Order
import tobyspring.hellospring.order.OrderRepository
import javax.sql.DataSource

class JdbcOrderRepository(dataSource: DataSource) : OrderRepository {
    private val jdbcClient: JdbcClient = JdbcClient.create(dataSource)

    @PostConstruct
    fun initDb() {
        jdbcClient.sql(
            """
            create table orders (id integer not null, no varchar(255), total numeric(38,2), primary key (id));
            alter table if exists orders drop constraint if exists UK43egxxciqr9ncgmxbdx2avi8n;
            alter table if exists orders add constraint UK43egxxciqr9ncgmxbdx2avi8n unique (no);
            create sequence orders_SEQ start with 1 increment by 50; 
            """,
        ).update()
    }

    override fun save(order: Order) {
        val query = jdbcClient.sql("select next value for orders_SEQ").query(Long::class.java).single()
        order.id = query

        jdbcClient.sql("insert into orders (no,total,id) values (?,?,?)")
            .params(order.no, order.total, order.id)
            .update()
    }
}
