package com.jsfcourse.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsfcourse.DAO.AccountsDAO;
import com.jsfcourse.entities.Accounts;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

@Named
@ViewScoped
public class AccountsListBB implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String PAGE_ACCOUNTS_EDIT = "accountsEdit?faces-redirect=true";

    private String accSurname;
    private List<Accounts> list;
    private Accounts selectedAccount;

    @EJB
    private AccountsDAO accountsDAO;

    @Inject
    private Flash flash;

    // gettery i settery
    public String getAccSurname() { 
        return accSurname; 
    }
    public void setAccSurname(String accSurname) { 
        this.accSurname = accSurname; 
    }

    public List<Accounts> getList() {
        if (list == null) {
            loadList();
        }
        return list;
    }
    
    public Accounts getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Accounts selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public void loadList() {
        Map<String,Object> searchParams = new HashMap<>();
        if (accSurname != null && !accSurname.isEmpty()) {
            searchParams.put("accSurname", accSurname);
        }
        list = accountsDAO.getList(searchParams);
    }

    public void search() {
        Map<String,Object> searchParams = new HashMap<>();
        if (accSurname != null && !accSurname.isEmpty()) {
            searchParams.put("accSurname", accSurname);
        }

        list = accountsDAO.getList(searchParams);
    }



    public String editAccounts(Accounts accounts){
        flash.put("accounts", accounts);
        return PAGE_ACCOUNTS_EDIT;
    }

    public void deleteAccount(Accounts account) {
        try {
            accountsDAO.removeAccountWithRoles(account);
            if (list != null) {
                list.remove(account);
            }
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Usunięto konto", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd przy usuwaniu", null));
            e.printStackTrace();
        }
    }
}
