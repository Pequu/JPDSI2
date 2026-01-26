package com.jsfcourse.login;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.jsfcourse.DAO.AccountsDAO;
import com.jsfcourse.entities.Accounts;
import java.util.HashMap;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_MAIN = "/pages/public/welcome?faces-redirect=true";
	private static final String PAGE_LOGIN = "/pages/login";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
        private String accLogin;
        private String accPass;
	private String pass;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Inject
	AccountsDAO accountsDAO;
        
        @Inject
        UserSessionBB userSession;

	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1.zweryfikuj login i zhashowane haslo
		Accounts accounts = accountsDAO.findByLogin(login, pass);

		// 2. jezeli zly login lub haslo to error
		if (accounts == null || !BCrypt.checkpw(pass, accounts.getAccPass())) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub hasło", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		// 3. jesli wszystko ok to dodaj RemoteClient i zapisz w Session
		               
		RemoteClient<Accounts> client = new RemoteClient<Accounts>(); //create new RemoteClient
                client.setDetails(accounts);
		
		List<String> roles = accountsDAO.getRolesForAccount(accounts); //get Accounts roles 
		
		if (roles != null) { //zapisz role w RemoteClient
			for (String role: roles) {
				client.getRoles().add(role);
			}
		}
	
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
                
                userSession.setLoggedAccount(accounts);           
		return PAGE_MAIN;
	}
        
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		session.invalidate();
		return PAGE_MAIN;
	}
        public String goToLogin() {
            return "/pages/login.xhtml?faces-redirect=true";
        }
	
}
