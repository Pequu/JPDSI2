package com.jsfcourse.room;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.enterprise.context.RequestScoped;

import jakarta.faces.application.FacesMessage;
import java.io.IOException;
import java.util.Date;

@Named
@RequestScoped
public class thanksBB {

    private Integer rprice;
    private String rname;
    private Date rdate;

    @Inject
    FacesContext context;

    public Integer getPrice() {
        return rprice;
    }

    public String getName() {
        return rname;
    }

    public Date getDate() {
        return rdate;
    }

    public void onLoad() {
        Flash flash = context.getExternalContext().getFlash();

        rprice = (Integer) flash.get("rprice");
        rname  = (String) flash.get("rname");
        rdate  = (Date) flash.get("rdate");

        if (rprice == null || rname == null || rdate == null) {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Błędne użycie systemu", null));
        }
    }
}
