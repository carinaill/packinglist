package de.cillgen.packinglist;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProducer {

	private static final String PERSISTENCE_UNIT = "default";

	@Produces
	@ApplicationScoped
	protected EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}

	protected void closeEntityManagerFactory(@Disposes EntityManagerFactory emf) {
		if (emf.isOpen()) {
			emf.close();
		}
	}
}
