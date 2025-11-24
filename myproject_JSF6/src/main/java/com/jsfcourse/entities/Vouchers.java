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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author rzyme
 */
@Entity
@Table(name = "vouchers")
@NamedQueries({
    @NamedQuery(name = "Vouchers.findAll", query = "SELECT v FROM Vouchers v"),
    @NamedQuery(name = "Vouchers.findByIdVoucher", query = "SELECT v FROM Vouchers v WHERE v.idVoucher = :idVoucher"),
    @NamedQuery(name = "Vouchers.findByVoName", query = "SELECT v FROM Vouchers v WHERE v.voName = :voName"),
    @NamedQuery(name = "Vouchers.findByVoAmount", query = "SELECT v FROM Vouchers v WHERE v.voAmount = :voAmount"),
    @NamedQuery(name = "Vouchers.findByVoIsActive", query = "SELECT v FROM Vouchers v WHERE v.voIsActive = :voIsActive"),
    @NamedQuery(name = "Vouchers.findByVoCreation", query = "SELECT v FROM Vouchers v WHERE v.voCreation = :voCreation"),
    @NamedQuery(name = "Vouchers.findByVoDeletion", query = "SELECT v FROM Vouchers v WHERE v.voDeletion = :voDeletion")})
public class Vouchers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVoucher")
    private Integer idVoucher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "voName")
    private String voName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "voAmount")
    private int voAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "voIsActive")
    private Boolean voIsActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "voCreation")
    @Temporal(TemporalType.DATE)
    private Date voCreation;
    @Column(name = "voDeletion")
    @Temporal(TemporalType.DATE)
    private Date voDeletion;
    @OneToMany(mappedBy = "vouchersidVoucher")
    private Collection<Reservations> reservationsCollection;

    public Vouchers() {
    }

    public Vouchers(Integer idVoucher) {
        this.idVoucher = idVoucher;
    }

    public Vouchers(Integer idVoucher, String voName, int voAmount, Boolean voIsActive, Date voCreation) {
        this.idVoucher = idVoucher;
        this.voName = voName;
        this.voAmount = voAmount;
        this.voIsActive = voIsActive;
        this.voCreation = voCreation;
    }

    public Integer getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(Integer idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }

    public int getVoAmount() {
        return voAmount;
    }

    public void setVoAmount(int voAmount) {
        this.voAmount = voAmount;
    }

    public Boolean getVoIsActive() {
        return voIsActive;
    }

    public void setVoIsActive(Boolean voIsActive) {
        this.voIsActive = voIsActive;
    }

    public Date getVoCreation() {
        return voCreation;
    }

    public void setVoCreation(Date voCreation) {
        this.voCreation = voCreation;
    }

    public Date getVoDeletion() {
        return voDeletion;
    }

    public void setVoDeletion(Date voDeletion) {
        this.voDeletion = voDeletion;
    }

    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoucher != null ? idVoucher.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vouchers)) {
            return false;
        }
        Vouchers other = (Vouchers) object;
        if ((this.idVoucher == null && other.idVoucher != null) || (this.idVoucher != null && !this.idVoucher.equals(other.idVoucher))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Vouchers[ idVoucher=" + idVoucher + " ]";
    }
    
}
