package com.jsfcourse.DAO;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Accounts;

//DAO - Data Access Object for Accounts entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class AccountsDAO {
	private final static String UNIT_NAME = "jsfcourse-escapePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Accounts accounts) {
		em.persist(accounts);
	}

	public Accounts merge(Accounts accounts) {
		return em.merge(accounts);
	}

	public void remove(Accounts accounts) {
		em.remove(em.merge(accounts));
	}

	public Accounts find(Object id) {
		return em.find(Accounts.class, id);
	}

	public List<Accounts> getFullList() {
		List<Accounts> list = null;

		Query query = em.createQuery("select a from Accounts a");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Accounts> getList(Map<String, Object> searchParams) {
		List<Accounts> list = null;

		// 1. Build query string with parameters
		String select = "select a ";
		String from = "from Accounts a ";
		String where = "";
		String orderby = "order by a.accSurname asc, a.accName";

		// search for surname
		String accSurname = (String) searchParams.get("accSurname");
		if (accSurname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "a.accSurname like :accSurname ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (accSurname != null) {
			query.setParameter("accSurname", accSurname+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Accounts objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
