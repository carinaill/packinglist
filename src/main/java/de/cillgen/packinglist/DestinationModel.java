package de.cillgen.packinglist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationModel implements Serializable {

	private String name;
	private Long id;
	private Map<Category, List<ItemModel>> itemModelsByCategory = new HashMap<>();

	public Map<Category, List<ItemModel>> getItemModelsByCategory() {

		return this.itemModelsByCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
