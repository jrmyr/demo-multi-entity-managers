package demo.mysql.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db")
public class Mysql implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
