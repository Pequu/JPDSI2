package com.jsfcourse.account;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsfcourse.DAO.AccountsDAO;
import com.jsfcourse.entities.Accounts;

@Named
@ViewScoped
public class AccountsEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ACCOUNTS_LIST = "accountsList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Accounts accounts = new Accounts();
	private Accounts loaded = null;

	@Inject
	FacesContext context;

	@EJB
	AccountsDAO accountsDAO;

	public Accounts getAccounts() {
		return accounts;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && accounts.getIdAccount() != null) {
				loaded = accountsDAO.find(accounts.getIdAccount());
			}
			if (loaded != null) {
				accounts = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("accountList.xhtml");
				// context.responseComplete();
				// }
			}
		}

	}

	public String saveData() {
		// no Accounts object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (accounts.getIdAccount() == null) {
				// new record
				accountsDAO.create(accounts);
			} else {
				// existing record
				accountsDAO.merge(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ACCOUNTS_LIST;
	}
}
