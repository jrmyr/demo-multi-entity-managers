package demo;

import demo.mysql.domain.Mysql;
import demo.mysql.domain.MysqlRepository;
import demo.postgres.domain.Postgres;
import demo.postgres.domain.PostgresRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class DemoMultiEntityManagers {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoMultiEntityManagers.class, args);

		PostgresRepository pgRepo = (PostgresRepository) context.getBean("postgresRepository");
		Postgres pg = new Postgres();
		pg.setName("New Postgres " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		pgRepo.save(pg);

		MysqlRepository myRepo = (MysqlRepository) context.getBean("mysqlRepository");
		Mysql my = new Mysql();
		my.setName("New Mysql " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		myRepo.save(my);

	}

}
