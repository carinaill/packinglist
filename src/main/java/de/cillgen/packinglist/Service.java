package de.cillgen.packinglist;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@ApplicationScoped
public class Service {

	@Inject
	private EntityManager em;

	public int testCount() {
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM DESTINATION");
		return ((Number) query.getSingleResult()).intValue();
	}

	public void testInit() {
		Destination destination = new Destination();
		destination.setName("Spanien");

		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
			em.persist(destination);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
	}

	public List<Card> testLoad() {
		List<Destination> destinations = em.createQuery("SELECT d FROM Destination d", Destination.class)
				.getResultList();
		Map<Long, List<String>> itemNamesByDestinationId = em.createQuery("SELECT i FROM Item i", Item.class)
				.getResultStream()
				.collect(Collectors.groupingBy(Item::getDestinationId,
						Collectors.mapping(Item::getName, Collectors.toList())));

		return destinations
				.stream()
				.map(dest -> toCard(dest, itemNamesByDestinationId))
				.collect(Collectors.toList());
	}

	private Card toCard(Destination dest, Map<Long, List<String>> itemNamesByDestinationId) {
		Card card = new Card(dest.getName());
		List<String> itemNames = itemNamesByDestinationId.getOrDefault(dest.getId(),
				Collections.emptyList());
		card.setItems(itemNames);
		return card;
	}

}
