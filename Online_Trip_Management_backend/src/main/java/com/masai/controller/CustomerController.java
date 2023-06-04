package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Customer;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.service.CustomerService;

@RestController("/customers")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<Customer> SaveCustomers( @RequestBody Customer customer) throws CustomerException {

		Customer c = customerService.registerNewCustomer(customer);
		return new ResponseEntity<>(c, HttpStatus.CREATED);

	}
	

	@PutMapping("/update/{sessionId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable String sessionId, @RequestBody Customer customer)
			throws CustomerException, LoginException {

		Customer newCustomer = customerService.updateCustomer(sessionId, customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delete/{sessionId}/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable String sessionId, @PathVariable Integer customerId)
			throws CustomerException, LoginException, AdminException {
		
		
		Customer newCustomer = customerService.deleteCustomer(sessionId, customerId);
		return new ResponseEntity<>(newCustomer, HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/getBySessionId/{sessionId}")
	public ResponseEntity<Customer> getCustomerBySessionId(@PathVariable String sessionId) throws LoginException, CustomerException {
		
		Customer customer  = customerService.getCustomerBySessionId(sessionId);
		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByCustomerId/{sessionId}/{customerId}")
	public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable String sessionId,@PathVariable Integer customerId) throws LoginException, CustomerException, AdminException {
		
		Customer customer  = customerService.getCustomerByCustomerId(sessionId, customerId);
		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllCustomer/{sessionId}")
	public ResponseEntity<List<Customer>> getAllCustomer(@PathVariable String sessionId) throws LoginException, CustomerException, AdminException {
		
		List<Customer> customers  = customerService.getAllCustomer(sessionId);
		return new ResponseEntity<>(customers, HttpStatus.ACCEPTED);
	}
		
}
