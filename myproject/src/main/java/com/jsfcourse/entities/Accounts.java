/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
@Table(name = "accounts")
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findByIdAccount", query = "SELECT a FROM Accounts a WHERE a.idAccount = :idAccount"),
    @NamedQuery(name = "Accounts.findByAccName", query = "SELECT a FROM Accounts a WHERE a.accName = :accName"),
    @NamedQuery(name = "Accounts.findByAccSurname", query = "SELECT a FROM Accounts a WHERE a.accSurname = :accSurname"),
    @NamedQuery(name = "Accounts.findByAccBirthDate", query = "SELECT a FROM Accounts a WHERE a.accBirthDate = :accBirthDate"),
    @NamedQuery(name = "Accounts.findByAccIsActive", query = "SELECT a FROM Accounts a WHERE a.accIsActive = :accIsActive"),
    @NamedQuery(name = "Accounts.findByAccCreation", query = "SELECT a FROM Accounts a WHERE a.accCreation = :accCreation"),
    @NamedQuery(name = "Accounts.findByAccDeletion", query = "SELECT a FROM Accounts a WHERE a.accDeletion = :accDeletion"),
    @NamedQuery(name = "Accounts.findByAccPass", query = "SELECT a FROM Accounts a WHERE a.accPass = :accPass"),
    @NamedQuery(name = "Accounts.findByAccLogin", query = "SELECT a FROM Accounts a WHERE a.accLogin = :accLogin")})
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAccount")
    private Integer idAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "accName")
    private String accName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "accSurname")
    private String accSurname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accBirthDate")
    @Temporal(TemporalType.DATE)
    private Date accBirthDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accIsActive")
    private Boolean accIsActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accCreation")
    @Temporal(TemporalType.DATE)
    private Date accCreation;
    @Column(name = "accDeletion")
    @Temporal(TemporalType.DATE)
    private Date accDeletion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "accPass")
    private String accPass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "accLogin")
    private String accLogin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountsidAccount")
    private Collection<Reservations> reservationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accidAccount")
    private Collection<Accroles> accrolesCollection;

    public Accounts() {
    }

    public Accounts(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Accounts(Integer idAccount, String accName, String accSurname, Date accBirthDate, Boolean accIsActive, Date accCreation, String accPass, String accLogin) {
        this.idAccount = idAccount;
        this.accName = accName;
        this.accSurname = accSurname;
        this.accBirthDate = accBirthDate;
        this.accIsActive = accIsActive;
        this.accCreation = accCreation;
        this.accPass = accPass;
        this.accLogin = accLogin;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccSurname() {
        return accSurname;
    }

    public void setAccSurname(String accSurname) {
        this.accSurname = accSurname;
    }

    public Date getAccBirthDate() {
        return accBirthDate;
    }

    public void setAccBirthDate(Date accBirthDate) {
        this.accBirthDate = accBirthDate;
    }

    public Boolean getAccIsActive() {
        return accIsActive;
    }

    public void setAccIsActive(Boolean accIsActive) {
        this.accIsActive = accIsActive;
    }

    public Date getAccCreation() {
        return accCreation;
    }

    public void setAccCreation(Date accCreation) {
        this.accCreation = accCreation;
    }

    public Date getAccDeletion() {
        return accDeletion;
    }

    public void setAccDeletion(Date accDeletion) {
        this.accDeletion = accDeletion;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public String getAccLogin() {
        return accLogin;
    }

    public void setAccLogin(String accLogin) {
        this.accLogin = accLogin;
    }

    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    public Collection<Accroles> getAccrolesCollection() {
        return accrolesCollection;
    }

    public void setAccrolesCollection(Collection<Accroles> accrolesCollection) {
        this.accrolesCollection = accrolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccount != null ? idAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.idAccount == null && other.idAccount != null) || (this.idAccount != null && !this.idAccount.equals(other.idAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Accounts[ idAccount=" + idAccount + " ]";
    }
    
}
