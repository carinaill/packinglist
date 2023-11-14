package de.cillgen.packinglist;

import java.util.List;

public class Card {
	
	private String title;
	private List<String> items;
	
	public Card(String title) {
		this.title = title;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
