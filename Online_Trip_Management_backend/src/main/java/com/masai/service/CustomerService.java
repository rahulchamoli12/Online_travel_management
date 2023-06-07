/**
 * 
 */
package com.masai.service;

import java.util.List;

import com.masai.dto.ChangePasswordByOTP;
import com.masai.dto.ResponseMessage;
import com.masai.entity.Customer;
import com.masai.entity.OTP;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.WrongOTPException;

/**
 * @author Aman_Maurya Following Functionality are available Register, Update,
 *         Delete, View Details based on (sessionId, customerId) or all
 *         customer, OTP functionality, Forgot Password with verification
 */
public interface CustomerService {

	public Customer registerNewCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(String sessionId, Customer customer) throws LoginException, CustomerException;

	public Customer deleteCustomer(String sessionId, Integer customerId)
			throws LoginException, AdminException, CustomerException;

	public Customer getCustomerBySessionId(String sessionId) throws LoginException, CustomerException;

	public Customer getCustomerByCustomerId(String sessionId, Integer customerId)
			throws LoginException, CustomerException, AdminException;

	public List<Customer> getAllCustomer(String sessionId) throws LoginException, AdminException, CustomerException;

	public ResponseMessage sendOtp(OTP otp) throws CustomerException;

	public ResponseMessage verifyOtpAndChangePassword(ChangePasswordByOTP changePass)
			throws CustomerException, WrongOTPException;

}
