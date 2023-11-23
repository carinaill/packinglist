package de.cillgen.packinglist;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

	@Inject
	private EntityManagerFactory emf;

	@Produces
	@RequestScoped
	protected EntityManager createEntityManager() {
		return this.emf.createEntityManager();
	}

	protected void closeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}