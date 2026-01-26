package com.jsfcourse.DAO;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Reservations;
import java.util.ArrayList;
import java.util.HashSet;

//DAO - Data Access Object for Accounts entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class ReservationsDAO {

    private final static String UNIT_NAME = "jsfcourse-escapePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Reservations reservation) {
        em.persist(reservation);
    }

    public Reservations merge(Reservations reservation) {
        return em.merge(reservation);
    }

    public void remove(Reservations reservation) {
        em.remove(em.merge(reservation));
    }

    public Reservations find(Object id) {
        return em.find(Reservations.class, id);
    }

    public List<Reservations> getFullList() {
        List<Reservations> list = null;
        Query query = em.createQuery("select r from Reservations r");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Reservations> getActiveByRoomId(Integer idRoom) {
        return em.createQuery(
            "select r from Reservations r " +
            "where r.roomsidRoom.idRoom = :idRoom " +
            "and r.resIsActive = true",
            Reservations.class
        ).setParameter("idRoom", idRoom)
         .getResultList();
    }


    
}