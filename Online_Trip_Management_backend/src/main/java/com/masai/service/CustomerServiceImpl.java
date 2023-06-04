package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.ChangePasswordByOTP;
import com.masai.dto.ResponseMessage;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.CurrentUserSession.Role;
import com.masai.entity.Customer;
import com.masai.entity.OTP;
import com.masai.entity.User;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.WrongOTPException;
import com.masai.repository.CustomerRepository;
import com.masai.repository.OtpRepository;
import com.masai.repository.SessionRepository;

/**
 * @author Aman_Maurya
 *
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private SessionRepository sessionRepo;

	@Autowired
	private EmailsenderService emailService;

	@Autowired
	private OtpRepository otpRepository;

	@Override
	public Customer registerNewCustomer(Customer customer) throws CustomerException {

		if (customer == null)
			throw new CustomerException("Customer Details Not Found!");

		Customer existingCustomer = customerRepo.findByEmail(customer.getEmail());
		if (existingCustomer != null)
			throw new CustomerException("Customer Found with same Email !");

		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(String sessionId, Customer customer) throws LoginException, CustomerException {

		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User Not Authorized!");

		Optional<Customer> opt = customerRepo.findById(cus.getUserId());

		if (opt.isEmpty())
			throw new CustomerException("Customer not Found!");

		Customer existingCustomer = opt.get();
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPassword(customer.getPassword());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setMobile_number(customer.getMobile_number());
		existingCustomer.setUsername(customer.getUsername());

		return customerRepo.save(existingCustomer);
	}

	@Override
	public Customer deleteCustomer(String sessionId, Integer customerId)
			throws LoginException, AdminException, CustomerException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User Not Authorized!");
		if (cus.getRole() == Role.ADMIN) {
			Optional<Customer> opt = customerRepo.findById(cus.getUserId());
			if (opt.isEmpty())
				throw new CustomerException("Customer not Found in Record!");

			customerRepo.delete(opt.get());
			return opt.get();
		}

		throw new AdminException("User Not Authorized!");

	}

	@Override
	public Customer getCustomerBySessionId(String sessionId) throws LoginException, CustomerException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User Not Authorized!");
		Optional<Customer> opt = customerRepo.findById(cus.getUserId());
		if (opt.isEmpty())
			throw new CustomerException("Customer not Found in Record!");

		return opt.get();
	}

	@Override
	public Customer getCustomerByCustomerId(String sessionId, Integer customerId)
			throws LoginException, CustomerException, AdminException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User Not Authorized!");
		if (cus.getRole() == Role.ADMIN) {
			Optional<Customer> opt = customerRepo.findById(customerId);
			if (opt.isEmpty())
				throw new CustomerException("Customer not Found in Record!");
			return opt.get();
		}

		throw new AdminException("User Not Authorized!");

	}

	@Override
	public List<Customer> getAllCustomer(String sessionId) throws LoginException, AdminException, CustomerException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User Not Authorized!");
		if (cus.getRole() == Role.ADMIN) {
			List<Customer> customers = customerRepo.findAll();
			if (customers.isEmpty())
				throw new CustomerException("Customer not Found in Record!");
			return customers;
		}

		throw new AdminException("User Not Authorized!");
	}

	@Override
	public ResponseMessage sendOtp(OTP otp) throws CustomerException {

		Customer customer = customerRepo.findByEmail(otp.getEmail());
		if (customer == null) {
			throw new CustomerException("Email not found");
		}
		Random randomOtp = new Random();
		int min = 100000, max = 999999;
		int otpCreated = randomOtp.nextInt(max - min) + min;
		otp.setOtp(otpCreated);
		// OTP will expire in 10 minutes
		otp.setCreatedAt(LocalDateTime.now());
		// Send an email to reset the password
		String emailSubject = "Reset Password";
		String emailBody = "Dear " + customer.getUsername() + ",\n\nYour OTP is " + otpCreated
				+ " to reset your password it is valid for 10 minutes" + "\n\nBest regards,\nThe Imperial Trip Team";
		emailService.sendEmail(customer.getEmail(), emailSubject, emailBody);

		otpRepository.save(otp);

		return new ResponseMessage("OTP has been send to your email");
	}

	public ResponseMessage verifyOtpAndChangePassword(ChangePasswordByOTP changePass)
			throws CustomerException, WrongOTPException {

		OTP otp = otpRepository.findByEmail(changePass.getEmail())
				.orElseThrow(() -> new CustomerException("User not found"));
		if (otp.getOtp() != changePass.getOtp()) {
			throw new WrongOTPException("Wrong otp");
		}
		Customer customer = customerRepo.findByEmail(otp.getEmail());
		customer.setPassword(changePass.getNewPassword());
		customerRepo.save(customer);

		return new ResponseMessage("Password updated successfully");
	}

}
