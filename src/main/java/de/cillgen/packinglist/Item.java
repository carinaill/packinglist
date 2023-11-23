package de.cillgen.packinglist;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "ITEM_ID")
	@SequenceGenerator(name = "ITEM_ID", sequenceName = "ITEM_SEQ", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "DESTINATION_ID", nullable = false)
	private long destinationId;

	@Column(nullable = false)
	private long done;

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

	public long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(long destinationId) {
		this.destinationId = destinationId;
	}

	public long getDone() {
		return done;
	}

	public void setDone(long done) {
		this.done = done;
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
		Item other = (Item) obj;
		return Objects.equals(id, other.id);
	}

}
