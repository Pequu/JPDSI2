package com.jsfcourse.DAO;

import com.jsfcourse.entities.Roles;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RolesDAO {

    @PersistenceContext
    private EntityManager em;

    public Roles findById(Integer id) {
        return em.find(Roles.class, id);
    }

    public List<Roles> findAll() {
        return em.createQuery("SELECT r FROM Roles r", Roles.class)
                 .getResultList();
    }
    
}
