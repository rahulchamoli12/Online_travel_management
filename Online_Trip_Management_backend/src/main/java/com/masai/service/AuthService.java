package com.masai.service;

import com.masai.dto.LoginDTO;
import com.masai.dto.ResponseMessage;
import com.masai.entity.CurrentUserSession;
import com.masai.exception.LoginException;


/**
 * @author Aman_Maurya
 *
 */

public interface AuthService {

	public CurrentUserSession logIntoApplication(LoginDTO Dto) throws LoginException;

	public ResponseMessage logoutFromApplication(String sessionId) throws LoginException;
}
