package com.jsfcourse.account;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.jsfcourse.DAO.AccountsDAO;
import com.jsfcourse.entities.Accounts;

@Named
@ViewScoped
public class AccountsEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ACCOUNTS_LIST = "accountsList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Accounts accounts = new Accounts();
	private Accounts loaded = null;

	@EJB
	AccountsDAO accountsDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Accounts getAccounts() {
		return accounts;
	}

	public void onLoad() throws IOException {
		// 1. load account passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Accounts) session.getAttribute("person");

		// 2. load account passed through flash
		loaded = (Accounts) flash.get("accounts");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			accounts = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
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
