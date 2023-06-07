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
 *         This class is used to provide functionality to perform Customer based
 *         operation Register, Update, Delete, View Details based on (sessionId,
 *         customerId) or all customer, OTP functionality, Forgot Password with
 *         verification
 */

@Service
public class CustomerServiceImpl implements CustomerService {

//	@Autowired is used to resolve CustomerRepository dependency 
	@Autowired 
	private CustomerRepository customerRepo;
//	@Autowired is used to resolve SessionRepository dependency 
	@Autowired
	private SessionRepository sessionRepo;
//	@Autowired is used to resolve EmailsenderService dependency 
	@Autowired
	private EmailsenderService emailService;
//	@Autowired is used to resolve OtpRepository dependency 
	@Autowired
	private OtpRepository otpRepository;

	/**
	 * 
	 * @param customer Details (Customer Entity Object)
	 * @return Customer Details.
	 * @throws CustomerException When Proper Customer Details Not Found!
	 * 
	 * After successfully registered Customer get mail from Our team .
	 */
	@Override
	public Customer registerNewCustomer(Customer customer) throws CustomerException {

		if (customer == null)
			throw new CustomerException("Customer Details Not Found!");

		Customer existingCustomer = customerRepo.findByEmail(customer.getEmail());
		if (existingCustomer != null)
			throw new CustomerException("Customer Found with same Email !");

		// Send an email to reset the password
		String emailSubject = "Registration Successfull";
		String emailBody = "Dear " + customer.getUsername()
				+ ",\n\nThank you for creating your account on Imperial Trevels."
				+ "\nWe hope our website will help you to provide best packages "
				+ "in affordable price range and with the best Hotels and Transport services."
				+ " We look forward to serving you. " + "\n\nBest regards,\nThe Imperial Travel Team";
		emailService.sendEmail(customer.getEmail(), emailSubject, emailBody);
		return customerRepo.save(customer);
	}

	/**
	 * 
	 * @param sessionId To verify actual customer or not 
	 * @param customer New Customer Details in Customer Object
	 * @return Updated Customer Details
	 * @throws LoginException if user not verify or wrong user found
	 * @throws CustomerException if customer was not present in Record
	 */
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
	/**
	 * 
	 * Mathod restricted for customer only Admin are eligible
	 * @param sessionId To verify actual customer or not 
	 * @param customerId To get Customer Detail to identification
	 * @return Deleted Customer Details to Show 
	 * @throws LoginException if User is not Login into our application
	 * @throws AdminException if user is not authorized 
	 * @throws CustomerException if customer details not found 
	 */
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

	/**
	 *  
	 * @param sessionId to verify user is login into our application or not
	 * @return Customer Details except password
	 * @throws LoginException if user is not login into our application
	 * @throws CustomerException if customer details not found
	 */
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
	/**
	 * 
	 * Mathod restricted for customer only Admin are eligible 
	 * @param sessionId to verify user is login into our application or not 
	 * @param customerId to get customer details from record
	 * @return required customer details
	 * @throws LoginException if user not login into our application
	 * @throws CustomerException
	 * @throws AdminException
	 */
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
	/**
	 * 
	 * Mathod restricted for customer only Admin are eligible 
	 * @param sessionId to verify Admin
	 * @return return list of all customer that are registered into record
	 * @throws LoginException if user are not login into our application
	 * @throws AdminException if user is not authorized to perform
	 * @throws CustomerException is not a single customer details found
	 */
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
	/**
	 * 
	 * @param otp to get email where we have to sent OTP 
	 * @return Response message if OTP send successfully
	 * @throws CustomerException if no customer details registered with given email ID 
	 * 
	 * if customer found with given email then a OTP send to that particular email to verify customer
	 * and that opt is store into record for 10 minute after 10 minute OTP get automatically deleted from record
	 */
	
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
				+ " to reset your password it is valid for 10 minutes" + "\n\nBest regards,\nThe Imperial Travel Team";
		emailService.sendEmail(customer.getEmail(), emailSubject, emailBody);

		otpRepository.save(otp);

		return new ResponseMessage("OTP has been send to your email");
	}
	/**
	 * 
	 * @param changePass in changePass Object user send OTP and new Password to verify and change customer password
	 * @return Response message if OTP get verified and password get updated
	 * @throws CustomerException if OTP not registered with given customer email 
	 * @throws WrongOTPException if provided OTP is not matched with recorded OTP
	 */
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
