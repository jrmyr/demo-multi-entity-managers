package demo.mysql.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlRepository extends JpaRepository<Mysql, Long> {
}
