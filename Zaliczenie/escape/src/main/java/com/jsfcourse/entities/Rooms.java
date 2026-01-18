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
@Table(name = "rooms")
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByIdRoom", query = "SELECT r FROM Rooms r WHERE r.idRoom = :idRoom"),
    @NamedQuery(name = "Rooms.findByRoomName", query = "SELECT r FROM Rooms r WHERE r.roomName = :roomName"),
    @NamedQuery(name = "Rooms.findByRoomDescription", query = "SELECT r FROM Rooms r WHERE r.roomDescription = :roomDescription"),
    @NamedQuery(name = "Rooms.findByRoomPrice", query = "SELECT r FROM Rooms r WHERE r.roomPrice = :roomPrice"),
    @NamedQuery(name = "Rooms.findByRoomCreation", query = "SELECT r FROM Rooms r WHERE r.roomCreation = :roomCreation"),
    @NamedQuery(name = "Rooms.findByRoomDeletion", query = "SELECT r FROM Rooms r WHERE r.roomDeletion = :roomDeletion"),
    @NamedQuery(name = "Rooms.findByRoomIsActive", query = "SELECT r FROM Rooms r WHERE r.roomIsActive = :roomIsActive"),
    @NamedQuery(name = "Rooms.findByRoomCover", query = "SELECT r FROM Rooms r WHERE r.roomCover = :roomCover")})
public class Rooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRoom")
    private Integer idRoom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "roomName")
    private String roomName;
    @Size(max = 500)
    @Column(name = "roomDescription")
    private String roomDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roomPrice")
    private int roomPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roomCreation")
    @Temporal(TemporalType.DATE)
    private Date roomCreation;
    @Column(name = "roomDeletion")
    @Temporal(TemporalType.DATE)
    private Date roomDeletion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "roomIsActive")
    private Boolean roomIsActive;
    @Column(name = "roomCover")
    private Integer roomCover;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomsidRoom")
    private Collection<Reservations> reservationsCollection;

    public Rooms() {
    }

    public Rooms(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Rooms(Integer idRoom, String roomName, int roomPrice, Date roomCreation, Boolean roomIsActive) {
        this.idRoom = idRoom;
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.roomCreation = roomCreation;
        this.roomIsActive = roomIsActive;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Date getRoomCreation() {
        return roomCreation;
    }

    public void setRoomCreation(Date roomCreation) {
        this.roomCreation = roomCreation;
    }

    public Date getRoomDeletion() {
        return roomDeletion;
    }

    public void setRoomDeletion(Date roomDeletion) {
        this.roomDeletion = roomDeletion;
    }

    public Boolean getRoomIsActive() {
        return roomIsActive;
    }

    public void setRoomIsActive(Boolean roomIsActive) {
        this.roomIsActive = roomIsActive;
    }

    public Integer getRoomCover() {
        return roomCover;
    }

    public void setRoomCover(Integer roomCover) {
        this.roomCover = roomCover;
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
        hash += (idRoom != null ? idRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.idRoom == null && other.idRoom != null) || (this.idRoom != null && !this.idRoom.equals(other.idRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Rooms[ idRoom=" + idRoom + " ]";
    }
    
}
