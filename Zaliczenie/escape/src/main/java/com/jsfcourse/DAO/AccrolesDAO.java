package com.jsfcourse.DAO;

import com.jsfcourse.entities.Accounts;
import com.jsfcourse.entities.Accroles;
import com.jsfcourse.entities.Roles;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccrolesDAO {

    @PersistenceContext
    private EntityManager em;

    public void create(Accroles accroles) {
        em.persist(accroles);
    }

    public void remove(Accroles accroles) {
        em.remove(em.merge(accroles));
    }
    
    public Accroles merge(Accroles accroles) {
		return em.merge(accroles);
	}
    
    public Accroles findByAccount(Integer accountId) {  
        return em.createQuery(
            "SELECT ar FROM Accroles ar WHERE ar.accidAccount.idAccount = :accountId", Accroles.class)
            .setParameter("accountId", accountId)
            .getSingleResult();   
}

}
