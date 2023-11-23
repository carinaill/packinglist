package de.cillgen.packinglist;

import java.io.Serializable;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ViewScoped
@Named
public class Overview implements Serializable {
	@Inject
	private Service service;

	private List<Card> destinations;

	public void init() {
		// service.testInit();
		destinations = service.testLoad();
	}

	public void onClick(String item) {
		System.out.println(item);

	}

	public List<Card> getDestinations() {
		return destinations;
	}

	public String getName() {
		return "Carina";
	}

}
