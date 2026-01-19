package com.jsfcourse.room;

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

import com.jsfcourse.DAO.RoomsDAO;
import com.jsfcourse.entities.Rooms;

@Named
@RequestScoped
public class RoomsListBB {

	private String roomName;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
        RoomsDAO roomsDAO;
		
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
        
	public List<Rooms> getFullList(){
		return roomsDAO.getFullList();
	}

	public List<Rooms> getList(){
		List<Rooms> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (roomName != null && roomName.length() > 0){
			searchParams.put("roomName", roomName);
		}
		
		//2. Get list
		list = roomsDAO.getList(searchParams);
		
		return list;
	}

}
