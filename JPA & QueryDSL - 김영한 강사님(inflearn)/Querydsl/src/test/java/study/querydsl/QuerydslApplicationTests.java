package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Example;
import study.querydsl.entity.QExample;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Example example = new Example();
		em.persist(example);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QExample qExample = new QExample("h");

		Example result = query
				.selectFrom(qExample)
				.fetchOne();

		assertThat(result).isEqualTo(example);
		assertThat(result.getId()).isEqualTo(example.getId());
	}
}
