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
@Table(name = "roles")
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByIdRole", query = "SELECT r FROM Roles r WHERE r.idRole = :idRole"),
    @NamedQuery(name = "Roles.findByRoleName", query = "SELECT r FROM Roles r WHERE r.roleName = :roleName"),
    @NamedQuery(name = "Roles.findByRoleIsActive", query = "SELECT r FROM Roles r WHERE r.roleIsActive = :roleIsActive"),
    @NamedQuery(name = "Roles.findByRoleCreation", query = "SELECT r FROM Roles r WHERE r.roleCreation = :roleCreation"),
    @NamedQuery(name = "Roles.findByRoleDeletion", query = "SELECT r FROM Roles r WHERE r.roleDeletion = :roleDeletion")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRole")
    private Integer idRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "roleName")
    private String roleName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roleIsActive")
    private Boolean roleIsActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roleCreation")
    @Temporal(TemporalType.DATE)
    private Date roleCreation;
    @Column(name = "roleDeletion")
    @Temporal(TemporalType.DATE)
    private Date roleDeletion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolesidRole")
    private Collection<Accroles> accrolesCollection;

    public Roles() {
    }

    public Roles(Integer idRole) {
        this.idRole = idRole;
    }

    public Roles(Integer idRole, String roleName, Boolean roleIsActive, Date roleCreation) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.roleIsActive = roleIsActive;
        this.roleCreation = roleCreation;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getRoleIsActive() {
        return roleIsActive;
    }

    public void setRoleIsActive(Boolean roleIsActive) {
        this.roleIsActive = roleIsActive;
    }

    public Date getRoleCreation() {
        return roleCreation;
    }

    public void setRoleCreation(Date roleCreation) {
        this.roleCreation = roleCreation;
    }

    public Date getRoleDeletion() {
        return roleDeletion;
    }

    public void setRoleDeletion(Date roleDeletion) {
        this.roleDeletion = roleDeletion;
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
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Roles[ idRole=" + idRole + " ]";
    }
    
}
