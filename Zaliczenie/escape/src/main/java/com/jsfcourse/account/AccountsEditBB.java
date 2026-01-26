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
import java.util.List;

import com.jsfcourse.DAO.AccountsDAO;
import com.jsfcourse.entities.Accounts;
import com.jsfcourse.DAO.AccrolesDAO;
import com.jsfcourse.entities.Accroles;
import com.jsfcourse.DAO.RolesDAO;
import com.jsfcourse.entities.Roles;


@Named
@ViewScoped
public class AccountsEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ACCOUNTS_LIST = "accountsList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Accounts accounts = new Accounts();
	private Accounts loadedA = null;
        private Accroles accroles = new Accroles();
	private Accroles loadedAR = null;
        private Roles roles = new Roles();
	private Roles loadedR = null;
        
        private List<Roles> rolesList;   // lista wszystkich ról do selectOneMenu
        private Integer selectedRoleId;   // ID wybranej roli w formularzu


	@EJB
	AccountsDAO accountsDAO;
        
        @EJB
        AccrolesDAO accrolesDAO;
        
        @EJB
        RolesDAO rolesDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Accounts getAccounts() {
		return accounts;
	}
        public Accroles getAccroles() {
		return accroles;
	}
         
        public Integer getSelectedRoleId() { 
            return selectedRoleId;
        }

        public void setSelectedRoleId(Integer selectedRoleId) { 
            this.selectedRoleId = selectedRoleId;
        }
        public List<Roles> getRolesList() {
            if (rolesList == null) {
                rolesList = rolesDAO.findAll(); // pobiera wszystkie role z bazy
            }
            return rolesList;
        }
        
            


	public void onLoad() throws IOException {
		// 1. load account passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Accounts) session.getAttribute("person");

		// 2. load account passed through flash
		loadedA = (Accounts) flash.get("accounts");

                
                

		// cleaning: attribute received => delete it from session
		if (loadedA != null) {
			accounts = loadedA;
                        
                        rolesList = rolesDAO.findAll();  // metoda w RolesDAO, która zwraca listę wszystkich ról
                        
                        Accroles accRole = accrolesDAO.findByAccount(accounts.getIdAccount());
                        if (accRole != null) {
                            selectedRoleId = accRole.getRolesidRole().getIdRole();
                        }

		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
            if (loadedA == null) {
                return PAGE_STAY_AT_THE_SAME;
            }

            try {
                // 1. Zapis konta
                if (accounts.getIdAccount() == null) {
                    // nowy rekord konta
                    accountsDAO.create(accounts);
                } else {
                    // istniejący rekord
                    accountsDAO.merge(accounts);
                }

                // 2. Obsługa roli w accroles
                Accroles accRole = accrolesDAO.findByAccount(accounts.getIdAccount());

                if (accRole == null) {
                    // konto nie ma przypisanej roli – tworzymy nową
                    accRole = new Accroles();
                    accRole.setAccidAccount(accounts);
                    accRole.setRolesidRole(rolesDAO.findById(selectedRoleId));
                    accrolesDAO.create(accRole);
                } else {
                    // konto ma już rolę – aktualizujemy
                    accRole.setRolesidRole(rolesDAO.findById(selectedRoleId));
                    accrolesDAO.merge(accRole);
                }

            } catch (Exception e) {
                e.printStackTrace();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd podczas zapisu", null));
                return PAGE_STAY_AT_THE_SAME;
            }

            return PAGE_ACCOUNTS_LIST;
        }

}
