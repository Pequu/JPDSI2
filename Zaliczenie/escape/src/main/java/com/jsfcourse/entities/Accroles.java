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
import java.io.Serializable;

/**
 *
 * @author rzyme
 */
@Entity
@Table(name = "accroles")
@NamedQueries({
    @NamedQuery(name = "Accroles.findAll", query = "SELECT a FROM Accroles a"),
    @NamedQuery(name = "Accroles.findByIdAR", query = "SELECT a FROM Accroles a WHERE a.idAR = :idAR")})
public class Accroles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAR")
    private Integer idAR;
    @JoinColumn(name = "acc_idAccount", referencedColumnName = "idAccount")
    @ManyToOne(optional = false)
    private Accounts accidAccount;
    @JoinColumn(name = "roles_idRole", referencedColumnName = "idRole")
    @ManyToOne(optional = false)
    private Roles rolesidRole;

    public Accroles() {
    }

    public Accroles(Integer idAR) {
        this.idAR = idAR;
    }

    public Integer getIdAR() {
        return idAR;
    }

    public void setIdAR(Integer idAR) {
        this.idAR = idAR;
    }

    public Accounts getAccidAccount() {
        return accidAccount;
    }

    public void setAccidAccount(Accounts accidAccount) {
        this.accidAccount = accidAccount;
    }

    public Roles getRolesidRole() {
        return rolesidRole;
    }

    public void setRolesidRole(Roles rolesidRole) {
        this.rolesidRole = rolesidRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAR != null ? idAR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accroles)) {
            return false;
        }
        Accroles other = (Accroles) object;
        if ((this.idAR == null && other.idAR != null) || (this.idAR != null && !this.idAR.equals(other.idAR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Accroles[ idAR=" + idAR + " ]";
    }
    
}
