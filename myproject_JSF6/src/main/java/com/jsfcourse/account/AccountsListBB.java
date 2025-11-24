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

@Named
@RequestScoped
public class AccountsListBB {
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String accName;
        private String accSurname;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
        AccountsDAO accountsDAO;
		
	public String getAccSurname() {
		return accSurname;
	}

	public void setAccSurname(String accSurname) {
		this.accSurname = accSurname;
	}
        
        public String getAccName() {
		return accName;
	}
        
        public void setName(String accName) {
		this.accName = accName;
	}

	public List<Accounts> getFullList(){
		return accountsDAO.getFullList();
	}

	public List<Accounts> getList(){
		List<Accounts> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (accSurname != null && accSurname.length() > 0){
			searchParams.put("accSurname", accSurname);
		}
		
		//2. Get list
		list = accountsDAO.getList(searchParams);
		
		return list;
	}

}
