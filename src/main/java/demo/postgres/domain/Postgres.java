package demo.postgres.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "db")
public class Postgres implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
