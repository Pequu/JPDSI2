package com.jsfcourse.login;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import com.jsfcourse.entities.Accounts;

@Named
@SessionScoped
public class UserSessionBB implements Serializable {

    private Accounts loggedAccount;

    public Accounts getLoggedAccount() {
        return loggedAccount;
    }

    public void setLoggedAccount(Accounts loggedAccount) {
        this.loggedAccount = loggedAccount;
    }

    public boolean isLogged() {
        return loggedAccount != null;
    }
}
