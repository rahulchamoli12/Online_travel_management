package com.masai.service;


import com.masai.dto.LoginDTO;
import com.masai.dto.LogoutDTO;
import com.masai.entity.CurrentUserSession;
import com.masai.exception.LoginException;

public interface LoginService {

	public CurrentUserSession logIntoApplication(LoginDTO Dto) throws LoginException;
	
	public LogoutDTO logoutFromApplication(String sessionId)throws LoginException;
}
