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

    // Znajdź voucher po ID
    public Vouchers find(Integer id) {
        if (id == null) return null;
        return em.find(Vouchers.class, id);
    }

    // Pobierz wszystkie aktywne vouchery
    public List<Vouchers> findActiveVouchers() {
        return em.createNamedQuery("Vouchers.findByVoIsActive", Vouchers.class)
                 .setParameter("voIsActive", true)
                 .getResultList();
    }

    // Zaktualizuj voucher (np. po użyciu)
    public void update(Vouchers voucher) {
        em.merge(voucher);
    }

    // Możesz też dodać create / delete jeśli potrzebujesz
}
