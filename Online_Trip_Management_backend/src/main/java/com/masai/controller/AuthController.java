package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.LoginDTO;
import com.masai.dto.LogoutDTO;
import com.masai.entity.CurrentUserSession;
import com.masai.exception.LoginException;
import com.masai.service.LoginService;

@RestController
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> loginIntoApplication(@RequestBody LoginDTO dto) throws LoginException{
		CurrentUserSession cus = loginService.logIntoApplication(dto);
		
		return new ResponseEntity<>(cus,HttpStatus.ACCEPTED);		
	}
	
	@PostMapping("/logout")
	public ResponseEntity<LogoutDTO> logoutFromApplication(@RequestParam("sessionId") String sessionId) throws LoginException{
		LogoutDTO out = loginService.logoutFromApplication(sessionId);
		
		return new ResponseEntity<>(out,HttpStatus.ACCEPTED);	
	}
	
}
