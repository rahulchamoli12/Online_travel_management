/**
 * 
 */
package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;

/**
 * @author Aman_Maurya
 *
 */
public interface CustomerService {

	public Customer registerNewCustomer(Customer customer)throws CustomerException;

	public Customer updateCustomer(String sessionId, Customer customer)throws LoginException,CustomerException;
	
	public Customer deleteCustomer(String sessionId,Integer customerId)throws LoginException,AdminException, CustomerException;
	
	public Customer getCustomerBySessionId(String sessionId) throws LoginException,CustomerException; 
	
	public Customer getCustomerByCustomerId(String sessionId,Integer customerId) throws LoginException,CustomerException,AdminException; 
	
	public List<Customer> getAllCustomer(String sessionId) throws LoginException,AdminException, CustomerException;
	
}
