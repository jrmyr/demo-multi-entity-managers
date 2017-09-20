package demo.postgres.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostgresTest {

	@Autowired
	private PostgresRepository postgresRepository;

	@Test
	public void save() {
		Postgres postgres = new Postgres();
		postgres.setName("Alternate-Postgres");
		assertThat(postgres.getId(), is(nullValue()));
		this.postgresRepository.save(postgres);
		assertThat(postgres.getId(), is(not(nullValue())));
	}

}
