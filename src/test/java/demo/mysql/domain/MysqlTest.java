package demo.mysql.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MysqlTest {

	@Autowired
	private MysqlRepository mysqlRepository;

	@Test
	public void save() {
		Mysql mysql = new Mysql();
		mysql.setName("Alternate-Mysql");
		assertThat(mysql.getId(), is(nullValue()));
		this.mysqlRepository.save(mysql);
		assertThat(mysql.getId(), is(not(nullValue())));
	}

}
