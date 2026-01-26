package com.jsfcourse.DAO;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Accounts;
import com.jsfcourse.entities.Accroles;
import com.jsfcourse.entities.Roles;
import java.util.ArrayList;
import java.util.HashSet;

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
        //wyszukanie loginu i czy się zgadza z haslem z bazy
        public Accounts findByLogin(String login, String pass) {
            
            ArrayList<String> roles = new ArrayList<String>();
            
            try {
                Query query = em.createQuery(
                    "SELECT a FROM Accounts a WHERE a.accLogin = :login"
                );
                query.setParameter("login", login);

                return (Accounts) query.getSingleResult();
            } catch (Exception e) {
                return null; // brak użytkownika lub błąd zapytania
            }
            
        }
        //pobranie rol z bazy
	public List<String> getRolesForAccount(Accounts account) {
            try {
                Query query = em.createQuery(
                    "SELECT r.roleName FROM Accroles ar JOIN ar.rolesidRole r WHERE ar.accidAccount = :account"
                );
                query.setParameter("account", account);

                return query.getResultList();
            } catch (Exception e) {
                return null;
            }
        }
        
        public Accounts findWithRoles(Integer id) {
            return em.createQuery("SELECT a FROM Accounts a LEFT JOIN FETCH a.accrolesCollection WHERE a.idAccount = :id", Accounts.class)
                     .setParameter("id", id)
                     .getSingleResult();
        }
        
        public void createUserWithRole(Accounts account, int roleId) {
        // zapis konta
        em.persist(account);
        em.flush(); // wymusza wygenerowanie ID konta

        // przypisanie roli
        Roles role = em.find(Roles.class, roleId); // zakładamy, że rola o ID=1 istnieje w DB
        if (role == null) {
            throw new RuntimeException("Rola nie istnieje w bazie!");
        }

        Accroles accRole = new Accroles();
        accRole.setAccidAccount(account);
        accRole.setRolesidRole(role);

        em.persist(accRole);
    }


}
