/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rzyme
 */
@Entity
@Table(name = "reservations")
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
    @NamedQuery(name = "Reservations.findByIdReservation", query = "SELECT r FROM Reservations r WHERE r.idReservation = :idReservation"),
    @NamedQuery(name = "Reservations.findByResDate", query = "SELECT r FROM Reservations r WHERE r.resDate = :resDate"),
    @NamedQuery(name = "Reservations.findByResPayment", query = "SELECT r FROM Reservations r WHERE r.resPayment = :resPayment"),
    @NamedQuery(name = "Reservations.findByResPrice", query = "SELECT r FROM Reservations r WHERE r.resPrice = :resPrice"),
    @NamedQuery(name = "Reservations.findByResIsActive", query = "SELECT r FROM Reservations r WHERE r.resIsActive = :resIsActive")})
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReservation")
    private Integer idReservation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resDate")
    @Temporal(TemporalType.DATE)
    private Date resDate;
    @Column(name = "resPayment")
    private Integer resPayment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resPrice")
    private int resPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resIsActive")
    private Boolean resIsActive;
    @JoinColumn(name = "accounts_idAccount", referencedColumnName = "idAccount")
    @ManyToOne(optional = false)
    private Accounts accountsidAccount;
    @JoinColumn(name = "rooms_idRoom", referencedColumnName = "idRoom")
    @ManyToOne(optional = false)
    private Rooms roomsidRoom;
    @JoinColumn(name = "vouchers_idVoucher", referencedColumnName = "idVoucher")
    @ManyToOne
    private Vouchers vouchersidVoucher;

    public Reservations() {
    }

    public Reservations(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Reservations(Integer idReservation, Date resDate, int resPrice, Boolean resIsActive) {
        this.idReservation = idReservation;
        this.resDate = resDate;
        this.resPrice = resPrice;
        this.resIsActive = resIsActive;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Date getResDate() {
        return resDate;
    }
    
    public Date getResDateDisplay() {
    if (resDate == null) return null;
    Calendar cal = Calendar.getInstance();
    cal.setTime(resDate);
    cal.set(Calendar.HOUR_OF_DAY, 12); // godzina południowa = bez przesunięcia dnia
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
}


    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public Integer getResPayment() {
        return resPayment;
    }

    public void setResPayment(Integer resPayment) {
        this.resPayment = resPayment;
    }

    public int getResPrice() {
        return resPrice;
    }

    public void setResPrice(int resPrice) {
        this.resPrice = resPrice;
    }

    public Boolean getResIsActive() {
        return resIsActive;
    }

    public void setResIsActive(Boolean resIsActive) {
        this.resIsActive = resIsActive;
    }

    public Accounts getAccountsidAccount() {
        return accountsidAccount;
    }

    public void setAccountsidAccount(Accounts accountsidAccount) {
        this.accountsidAccount = accountsidAccount;
    }

    public Rooms getRoomsidRoom() {
        return roomsidRoom;
    }

    public void setRoomsidRoom(Rooms roomsidRoom) {
        this.roomsidRoom = roomsidRoom;
    }

    public Vouchers getVouchersidVoucher() {
        return vouchersidVoucher;
    }

    public void setVouchersidVoucher(Vouchers vouchersidVoucher) {
        this.vouchersidVoucher = vouchersidVoucher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservation != null ? idReservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.idReservation == null && other.idReservation != null) || (this.idReservation != null && !this.idReservation.equals(other.idReservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Reservations[ idReservation=" + idReservation + " ]";
    }
    
}
