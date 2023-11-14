package de.cillgen.packinglist;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@ApplicationScoped
public class Service {

	public int testCount() {
		EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM DESTINATION");
		return ((Number) query.getSingleResult()).intValue();
	}
	
}
