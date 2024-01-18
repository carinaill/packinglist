package de.cillgen.packinglist;

import java.io.Serializable;

public class ItemModel implements Serializable {

	private Long id;
	private String name;
	private boolean done;
	private long quantity;
	private Category category;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getDone() {
		return this.done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
