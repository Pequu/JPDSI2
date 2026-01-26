package com.jsfcourse.login;

import com.jsfcourse.entities.Accounts;
import com.jsfcourse.entities.Accroles;
import com.jsfcourse.entities.Roles;
import com.jsfcourse.DAO.AccountsDAO;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.Date;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.mindrot.jbcrypt.BCrypt;

@Named
@RequestScoped
public class RegisterBB {

    private String login;
    private String name;
    private String surname;
    private String pass;
    private String pass2;
    private String email;
    private Date birthdate;
    private String hashedPass;

    @EJB
    private AccountsDAO accountsDAO; 

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
    public String getPass2() { return pass2; }
    public void setPass2(String pass2) { this.pass2 = pass2; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String goToRegister() {
            return "/pages/register.xhtml?faces-redirect=true";
        }
    
    public String doRegister() {
        FacesContext context = FacesContext.getCurrentInstance();

        // walidacja hasła
        if (!pass.equals(pass2)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hasła nie są identyczne!", ""));
            return null;
        }
        
        // Hashowanie hasla
        hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());


        try {
            Accounts account = new Accounts();
            account.setAccLogin(login);
            account.setAccName(name);
            account.setAccSurname(surname);
            account.setAccPass(hashedPass); 
            account.setAccBirthDate(birthdate);
            account.setAccCreation(new Date());
            account.setAccIsActive(true);

            accountsDAO.createUserWithRole(account, 3); // 3 = rola user

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Konto zostało utworzone!", ""));
            return "login?faces-redirect=true"; 
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas rejestracji: " + e.getMessage(), ""));
            return null;
        }
    }
}
