package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@PersistenceContext
	EntityManager em;



	@Test
	void contextLoads() {
		Hello hello=new Hello();
		em.persist(hello);

		JPAQueryFactory query=new JPAQueryFactory(em);
		QHello qHello=new QHello("h");
		Hello hello1 = query.selectFrom(qHello).fetchOne();

		Assertions.assertThat(hello1).isEqualTo(hello);
	}

}
