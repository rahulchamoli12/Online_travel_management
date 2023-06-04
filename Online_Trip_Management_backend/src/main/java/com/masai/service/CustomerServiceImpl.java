package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.CurrentUserSession;
import com.masai.entity.CurrentUserSession.Role;
import com.masai.entity.Customer;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.repository.CustomerRepository;
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

}
