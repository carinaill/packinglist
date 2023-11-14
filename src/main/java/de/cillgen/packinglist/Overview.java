package de.cillgen.packinglist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class Overview {

	private List<Card> destinations;

	public void init() {
		this.destinations = new ArrayList<>();
		
		Card spain = new Card("Spanien");
		spain.setItems(Arrays.asList("flipflops", "sonnencreme", "sonnencreme", "sonnencreme"));
		this.destinations.add(spain);
		
		Card norway = new Card("Norwegen");
		norway.setItems(Arrays.asList("handschuhe", "mütze", "sonnencreme"));
		this.destinations.add(norway);
		
		Card costaRica = new Card("Costa Rica");
		costaRica.setItems(Arrays.asList("sonnenbrille", "cappie"));
		this.destinations.add(costaRica);
		
		Card spain1 = new Card("Spanien1");
		spain1.setItems(Arrays.asList("flipflops", "sonnencreme"));
		this.destinations.add(spain1);
		Card spain2 = new Card("Spanien2");
		spain2.setItems(Arrays.asList("flipflops", "sonnencreme", "sonnencreme", "sonnencreme"));
		this.destinations.add(spain2);
		Card spain3 = new Card("Spanien3");
		spain3.setItems(Arrays.asList("flipflops", "sonnencreme"));
		this.destinations.add(spain3);
		Card spain4 = new Card("Spanien4");
		spain4.setItems(Arrays.asList("flipflops", "sonnencreme", "sonnencreme"));
		this.destinations.add(spain4);
	}

	public List<Card> getDestinations() {
		return destinations;
	}

	public String getName() {
		return "Carina";
	}

}
