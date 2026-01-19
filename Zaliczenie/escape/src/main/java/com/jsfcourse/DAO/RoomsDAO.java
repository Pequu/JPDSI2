package com.jsfcourse.DAO;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Rooms;
import java.util.ArrayList;
import java.util.HashSet;

//DAO - Data Access Object for Accounts entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class RoomsDAO {

    private final static String UNIT_NAME = "jsfcourse-escapePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Rooms room) {
        em.persist(room);
    }

    public Rooms merge(Rooms room) {
        return em.merge(room);
    }

    public void remove(Rooms room) {
        em.remove(em.merge(room));
    }

    public Rooms find(Object id) {
        return em.find(Rooms.class, id);
    }

    public List<Rooms> getFullList() {
        List<Rooms> list = null;
        Query query = em.createQuery("select r from Rooms r");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Rooms> getList(Map<String, Object> searchParams) {
        List<Rooms> list = null;

        String select = "select r ";
        String from = "from Rooms r ";
        String where = "";
        String orderby = "order by r.roomName asc";

        String roomName = (String) searchParams.get("roomName");
        if (roomName != null) {
            where = "where r.roomName like :roomName ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (roomName != null) {
            query.setParameter("roomName", roomName + "%");
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}