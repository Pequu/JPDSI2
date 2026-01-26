package com.jsfcourse.room;

import com.jsfcourse.entities.Reservations;
import com.jsfcourse.DAO.ReservationsDAO;
import com.jsfcourse.entities.Rooms;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.constraints.Future;
import java.util.Calendar;

@Named
@ViewScoped
public class CalendarView implements Serializable {
    
    private Rooms loaded = null;
    
    @Inject
    Flash flash;
   
    @EJB
    ReservationsDAO reservationsDAO;
    
    @Future
    private Date date;
    private Date maxDate;

    private List<Date> validDates;

    @PostConstruct
    public void init() {
        validDates = new ArrayList<>();
        

        Date today = normalize(new Date());
        long oneDay = 24L * 60 * 60 * 1000;

        // Generujemy 30 dni od dziś
        for (int i = 0; i < 30; i++) {
            validDates.add(new Date(today.getTime() + i * oneDay));
        }
        //Wyszukujemy rezerwacje i usuwamy z listy dostepnych dat
        loaded = (Rooms) flash.get("rooms");

        if (loaded != null) {
            List<Reservations> reservationsList = reservationsDAO.getActiveByRoomId(loaded.getIdRoom());

            for (Reservations r : reservationsList) {
                Date reserved = normalize(r.getResDate());
                validDates.remove(reserved);
            }
        }
        
        maxDate = new Date(today.getTime());
    }

    private Date normalize(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    


    public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Date Selected",
                        format.format(event.getObject())));
    }

    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }

    // Gettery/settery
    public Date getDate() { 
        return date; 
    }
    
    public void setDate(Date date) { 
        this.date = date; 
    }

    public List<Date> getValidDates() {
        return validDates; 
    }
    
    public void setValidDates(List<Date> validDates) {
        this.validDates = validDates; 
    }
    
    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
