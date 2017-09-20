package demo.postgres.domain;

import demo.postgres.domain.Postgres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresRepository extends JpaRepository<Postgres, Long> {
}
