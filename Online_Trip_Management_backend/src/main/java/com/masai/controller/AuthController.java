package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.ChangePasswordByOTP;
import com.masai.dto.LoginDTO;
import com.masai.dto.ResponseMessage;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.OTP;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.WrongOTPException;
import com.masai.service.CustomerService;
import com.masai.service.AuthService;

@RestController
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthService loginService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> loginIntoApplication(@RequestBody LoginDTO dto) throws LoginException{
		CurrentUserSession cus = loginService.logIntoApplication(dto);
		
		return new ResponseEntity<>(cus,HttpStatus.ACCEPTED);		
	}
	
	@PostMapping("/logout")
	public ResponseEntity<ResponseMessage> logoutFromApplication(@RequestParam("sessionId") String sessionId) throws LoginException{
		ResponseMessage responseMessage = loginService.logoutFromApplication(sessionId);
		
		return new ResponseEntity<>(responseMessage,HttpStatus.ACCEPTED);	
	}
	
	@PostMapping("/forgotpassword/{email}")
	public ResponseEntity<ResponseMessage> sendOTP(@PathVariable String email) throws CustomerException {
		email = email.toLowerCase();
		OTP otp = new OTP();
		otp.setEmail(email);
		ResponseMessage responseMessage = customerService.sendOtp(otp);
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	@PutMapping("/verify")
	public ResponseEntity<ResponseMessage> verifyOTP(@RequestBody ChangePasswordByOTP changepass) throws CustomerException, WrongOTPException{
		ResponseMessage responseMessage = customerService.verifyOtpAndChangePassword(changepass);
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}

	
	
}
