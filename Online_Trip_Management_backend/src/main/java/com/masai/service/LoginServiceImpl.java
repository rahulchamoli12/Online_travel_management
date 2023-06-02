package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.LoginDTO;
import com.masai.dto.LogoutDTO;
import com.masai.entity.Admin;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.CurrentUserSession.Role;
import com.masai.entity.Customer;
import com.masai.exception.LoginException;
import com.masai.repository.AdminRepository;
import com.masai.repository.CustomerRepository;
import com.masai.repository.SessionRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private SessionRepository sessionRepo;

	@Override
	public CurrentUserSession logIntoApplication(LoginDTO Dto) throws LoginException {

		Customer customer = customerRepo.findByEmail(Dto.getEmail());
		if (customer == null) {
			Admin admin = adminRepo.findByEmail(Dto.getEmail());
			if (admin == null) {
				throw new LoginException("Wrong Credential !");
			}
			if (Dto.getPassword().equals(admin.getPassword())) {

				CurrentUserSession cus = new CurrentUserSession();
				String sessionId = RandomKeyGenerator.generateRandomString(8);
				cus.setLoginTime(LocalDateTime.now());
				cus.setRole(Role.ADMIN);
				cus.setUserId(admin.getUserId());
				cus.setSessionId(sessionId);

				return sessionRepo.save(cus);
			} else {
				throw new LoginException("Wrong Credential!");
			}
		} else {

			Optional<CurrentUserSession> loginUserSession = sessionRepo.findById(customer.getUserId());

			if (Dto.getPassword().equals(customer.getPassword())) {

				if (loginUserSession.isPresent())
					sessionRepo.delete(loginUserSession.get());

				CurrentUserSession cus = new CurrentUserSession();
				String sessionId = RandomKeyGenerator.generateRandomString(8);
				cus.setLoginTime(LocalDateTime.now());
				cus.setRole(Role.CUSTOMER);
				cus.setUserId(customer.getUserId());
				cus.setSessionId(sessionId);

				return sessionRepo.save(cus);
			} else {
				throw new LoginException("Wrong Credential!");
			}
		}
	}

	@Override
	public LogoutDTO logoutFromApplication(String sessionId) throws LoginException {

		CurrentUserSession loginUserSession = sessionRepo.findBySessionId(sessionId);
		if (loginUserSession == null)
			throw new LoginException("Not login into application!");
		
		sessionRepo.delete(loginUserSession);
		return new LogoutDTO("Successfully logout...");
	}

}
