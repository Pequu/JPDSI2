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
import com.jsfcourse.DAO.VouchersDAO;
import com.jsfcourse.entities.Rooms;
import com.jsfcourse.entities.Reservations;
import com.jsfcourse.entities.Accounts;
import com.jsfcourse.entities.Vouchers;
import com.jsfcourse.login.UserSessionBB;

@Named
@ViewScoped
public class RoomsReserveBB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PAGE_ROOM_LIST = "/pages/public/thanks?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Rooms rooms = new Rooms();
    private Rooms loaded;

    private Date reservationDate;
    private Integer paymentMethod;

    private String voucherCode;
    private Vouchers voucher;
    private Integer finalPrice;

    @EJB
    RoomsDAO roomsDAO;

    @EJB
    ReservationsDAO reservationsDAO;

    @EJB
    VouchersDAO vouchersDAO;

    @Inject
    UserSessionBB userSession;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    // gettery i settery

    public Rooms getRooms() {
        return rooms;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public Integer getFinalPrice() {
        return finalPrice != null ? finalPrice : rooms.getRoomPrice();
    }

    // on load

    public void onLoad() throws IOException {
        loaded = (Rooms) flash.get("rooms");

        if (loaded != null) {
            rooms = loaded;
            finalPrice = rooms.getRoomPrice();
        } else {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Błędne użycie systemu", null));
        }
    }

    // zapis

    public String saveData() {

        if (loaded == null || reservationDate == null) {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Błąd", "Nie wybrano daty"));
            return PAGE_STAY_AT_THE_SAME;
        }

        Accounts account = userSession.getLoggedAccount();

        if (account == null) {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Zaloguj się", null));
            return null;
        }

        // voucher
        finalPrice = rooms.getRoomPrice();
        voucher = null;

        if (voucherCode != null && !voucherCode.isBlank()) {

            voucher = vouchersDAO.findActiveByName(voucherCode);

            if (voucher == null) {
                context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Nieprawidłowy lub nieaktywny voucher", null));
                return PAGE_STAY_AT_THE_SAME;
            }

            double discount = voucher.getVoAmount(); // np. 15 = 15%
            double discounted = rooms.getRoomPrice() * (1 - discount / 100);
            finalPrice = (int) Math.round(discounted);
        }

        // rezerwacja
        Reservations r = new Reservations();

        r.setResDate(reservationDate);
        r.setResPrice(finalPrice);
        r.setResIsActive(false);

        r.setRoomsidRoom(rooms);
        r.setAccountsidAccount(account);
        r.setResPayment(paymentMethod);
        r.setVouchersidVoucher(voucher);

        reservationsDAO.create(r);      

        context.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sukces",
                "Rezerwacja została zapisana"));

        flash.put("rprice", finalPrice);
        flash.put("rname",rooms.getRoomName());
        flash.put("rdate", reservationDate);
        return PAGE_ROOM_LIST;
    }
}
