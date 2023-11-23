package de.cillgen.packinglist;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Destination {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "DESTINATION_ID")
	@SequenceGenerator(name = "DESTINATION_ID", sequenceName = "DESTINATION_SEQ", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destination other = (Destination) obj;
		return Objects.equals(id, other.id);
	}

}
