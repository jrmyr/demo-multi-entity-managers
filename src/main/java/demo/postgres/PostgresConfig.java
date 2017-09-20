package demo.postgres;

import demo.postgres.domain.Postgres;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "postgresEntityManager",
		transactionManagerRef = "postgresTransactionManager",
		basePackageClasses = Postgres.class)
public class PostgresConfig {

	@Autowired(required = false)
	private PersistenceUnitManager persistenceUnitManager;

	@Bean
	@ConfigurationProperties("app.pg.jpa")
	public JpaProperties postgresJpaProperties() {
		return new JpaProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "app.pg.datasource")
	public DataSource postgresDataSource() {
		return (DataSource) DataSourceBuilder.create().type(DataSource.class).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean postgresEntityManager(
			JpaProperties postgresJpaProperties) {
		EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(postgresJpaProperties);
		return builder
				.dataSource(postgresDataSource())
				.packages(Postgres.class)
				.persistenceUnit("postgresDs")
				.build();
	}

	@Bean
	@Primary
	public JpaTransactionManager postgresTransactionManager(EntityManagerFactory postgresEntityManager) {
		return new JpaTransactionManager(postgresEntityManager);
	}

	private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties postgresJpaProperties) {
		JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(postgresJpaProperties);
		return new EntityManagerFactoryBuilder(jpaVendorAdapter,
				postgresJpaProperties.getProperties(), this.persistenceUnitManager);
	}

	private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(jpaProperties.isShowSql());
		adapter.setDatabase(jpaProperties.getDatabase());
		adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
		adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
		return adapter;
	}

}
