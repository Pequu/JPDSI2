package com.jsfcourse.worker;

import com.jsfcourse.entities.Reservations;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ReservationsListBB implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    private List<Reservations> list;

    // Pole do wyszukiwania po numerze pokoju
    private String roomNumberSearch;

    // Flaga do filtrowania przeterminowanych
    private boolean showExpiredOnly = false;

    // gettery i settery
    public List<Reservations> getList() {
        if (list == null) {
            refreshList();
        }
        return list;
    }

    public String getRoomNumberSearch() {
        return roomNumberSearch;
    }

    public void setRoomNumberSearch(String roomNumberSearch) {
        this.roomNumberSearch = roomNumberSearch;
    }

    public boolean isShowExpiredOnly() {
        return showExpiredOnly;
    }

    public void setShowExpiredOnly(boolean showExpiredOnly) {
        this.showExpiredOnly = showExpiredOnly;
    }

    // odswiezanie listy
    private void refreshList() {
        String baseQuery = "SELECT r FROM Reservations r WHERE 1=1";

        // Filtrowanie przeterminowanych
        if (showExpiredOnly) {
            baseQuery += " AND r.resDate > CURRENT_DATE";
        }

        try {
            if (roomNumberSearch != null && !roomNumberSearch.trim().isEmpty()) {
                int roomNumber = Integer.parseInt(roomNumberSearch.trim());
                baseQuery += " AND r.roomsidRoom.idRoom = :roomNumber";
                list = em.createQuery(baseQuery, Reservations.class)
                         .setParameter("roomNumber", roomNumber)
                         .getResultList();
                return;
            }
        } catch (NumberFormatException e) {
            // jeśli wpisano niepoprawny numer, ignorujemy filtr pokoju
        }

        // W przeciwnym razie pobieramy wszystkie / tylko przeterminowane
        list = em.createQuery(baseQuery, Reservations.class).getResultList();
    }

    // wyszukanie po numerze
    public void searchByRoom() {
        refreshList();
    }

    // przelaczanie filtra
    public void toggleExpiredFilter() {
        showExpiredOnly = !showExpiredOnly;
        refreshList();
    }

    // przelaczanie statusu
    @Transactional
    public void switchReservation(Reservations r) {
        if (r != null) {
            Reservations res = em.find(Reservations.class, r.getIdReservation());
            if (res != null) {
                res.setResIsActive(!res.getResIsActive());
                em.merge(res);
                refreshList();
            }
        }
    }

    // usuwanie rezerawcji
    @Transactional
    public void deleteReservation(Reservations r) {
        if (r != null) {
            Reservations res = em.find(Reservations.class, r.getIdReservation());
            if (res != null) {
                em.remove(res);
                refreshList();
            }
        }
    }
}
