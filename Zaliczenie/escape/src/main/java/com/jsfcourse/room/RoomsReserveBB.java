package com.jsfcourse.room;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsfcourse.DAO.RoomsDAO;
import com.jsfcourse.DAO.ReservationsDAO;
import com.jsfcourse.entities.Rooms;
import com.jsfcourse.entities.Reservations;
import com.jsfcourse.entities.Accounts;
import com.jsfcourse.login.UserSessionBB;

@Named
@ViewScoped
public class RoomsReserveBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ROOM_LIST = "/pages/public/roomList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

        private Rooms rooms = new Rooms();
        private Rooms loaded = null;
        private Date reservationDate;
        private Integer paymentMethod;;
        
	@EJB
        RoomsDAO roomsDAO;

        @EJB
        ReservationsDAO reservationsDAO;

        @Inject
        UserSessionBB userSession;   

        @Inject
        FacesContext context;

        @Inject
        Flash flash;        

	public Rooms getRooms() {
		return rooms;
	}

        public Date getReservationDate() {
            return reservationDate;
        }
              
        public Integer getPaymentMethod() {
            return paymentMethod; 
        }
        
        public void setPaymentMethod(Integer paymentMethod) {
            this.paymentMethod = paymentMethod; 
        }


        public void setReservationDate(Date reservationDate) {
            this.reservationDate = reservationDate;
        }

  
	public void onLoad() throws IOException {
		loaded = (Rooms) flash.get("rooms");
	
		if (loaded != null) {
			rooms = loaded;
                                          
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}

	public String saveData() {

        // brak danych
        if (loaded == null || reservationDate == null) {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Błąd", "Nie wybrano daty"));
            return PAGE_STAY_AT_THE_SAME;
        }

        // brak loginu
        Accounts account = userSession.getLoggedAccount(); 

        if (account == null) {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Zaloguj się", null));
            return null;
        }

        // zapis danych w bazie danych
        Reservations r = new Reservations();

        r.setResDate(reservationDate);
        r.setResPrice(rooms.getRoomPrice());
        r.setResIsActive(false);

        r.setRoomsidRoom(rooms);
        r.setAccountsidAccount(account);

        // opcjonalne pola
        r.setResPayment(paymentMethod);
        r.setVouchersidVoucher(null);

        reservationsDAO.create(r);

        context.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sukces",
                "Rezerwacja została zapisana"));

        return PAGE_ROOM_LIST;
    }
}