package de.cillgen.packinglist;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@ApplicationScoped
public class Service {

	@Inject
	private EntityManager em;

	public List<DestinationModel> load() {
		List<Destination> destinations = em
				.createQuery("SELECT d FROM Destination d ORDER BY d.id DESC", Destination.class)
				.getResultList();
		Map<Long, List<Item>> itemNamesByDestinationId = em
				.createQuery("SELECT i FROM Item i ORDER BY i.id ASC", Item.class)
				.getResultStream()
				.collect(Collectors.groupingBy(Item::getDestinationId, Collectors.toList()));

		return destinations
				.stream()
				.map(dest -> toDestinationModel(dest, itemNamesByDestinationId))
				.toList();
	}

	private DestinationModel toDestinationModel(Destination dest, Map<Long, List<Item>> itemNamesByDestinationId) {
		DestinationModel destinationModel = new DestinationModel();
		Map<Category, List<ItemModel>> itemModelsByCategory = itemNamesByDestinationId
				.getOrDefault(dest.getId(), Collections.emptyList()).stream().map(this::toItemModel)
				.collect(Collectors.groupingBy(ItemModel::getCategory));
		destinationModel.setId(dest.getId());
		destinationModel.setName(dest.getName());
		destinationModel.getItemModelsByCategory().putAll(itemModelsByCategory);
		return destinationModel;
	}

	private ItemModel toItemModel(Item item) {
		ItemModel itemModel = new ItemModel();
		itemModel.setId(item.getId());
		itemModel.setName(item.getName());
		itemModel.setQuantity(item.getQuantity());
		itemModel.setCategory(item.getCategory());
		itemModel.setDone(item.getDone() == 1);
		return itemModel;
	}

	public Item toItem(ItemModel itemModel, Long destId) {
		Item item = new Item();
		item.setDestinationId(destId);
		item.setName(itemModel.getName());
		item.setQuantity(itemModel.getQuantity());
		item.setCategory(itemModel.getCategory());
		int done = itemModel.getDone() ? 0 : 1;
		item.setDone(done);
		return item;
	}

	public Destination toDestination(String destinationName) {
		Destination destination = new Destination();
		destination.setName(destinationName);
		return destination;
	}

	public void updateItem(ItemModel itemModel) throws ServiceException {
		Item item = em.find(Item.class, itemModel.getId());
		long status = itemModel.getDone() ? 0 : 1;
		item.setDone(status);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(item);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new ServiceException(e);
		}
	}

	public void removeItem(ItemModel itemModel) throws ServiceException {
		Item item = em.find(Item.class, itemModel.getId());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(item);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new ServiceException(e);
		}
	}

	public void addItem(Item item) throws ServiceException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(item);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new ServiceException(e);
		}
	}

	public void removeDestination(DestinationModel destinationModel) throws ServiceException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			destinationModel.getItemModelsByCategory().forEach((key, itemModelList) -> itemModelList.forEach(i -> {
				Item item = em.find(Item.class, i.getId());
				em.remove(item);
			}));
			Destination destination = em.find(Destination.class, destinationModel.getId());
			em.remove(destination);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new ServiceException(e);
		}
	}

	public void addDestination(Destination destination) throws ServiceException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(destination);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new ServiceException(e);
		}
	}
}