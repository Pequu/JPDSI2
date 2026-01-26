package com.jsfcourse.DAO;

import com.jsfcourse.entities.Vouchers;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VouchersDAO {

    @PersistenceContext
    private EntityManager em;

   public Vouchers findActiveByName(String name) {
        try {
            return em.createQuery(
                "SELECT v FROM Vouchers v WHERE v.voName = :name AND v.voIsActive = true",
                Vouchers.class)
                .setParameter("name", name)
                .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

