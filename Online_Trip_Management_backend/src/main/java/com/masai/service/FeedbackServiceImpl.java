package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.dto.FeedbackResponse;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.Customer;
import com.masai.entity.Feedback;
import com.masai.entity.Package;
import com.masai.exception.CustomerException;
import com.masai.exception.FeedbackException;
import com.masai.exception.LoginException;
import com.masai.repository.CustomerRepository;
import com.masai.repository.FeedbackRepository;
import com.masai.repository.PackageRepository;
import com.masai.repository.SessionRepository;

public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private SessionRepository sessRepo;
	@Autowired
	private FeedbackRepository feedbackRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PackageRepository packageRepo;
	
	@Override
	public FeedbackResponse addFeedback(String sessionId, Feedback feedback) throws FeedbackException, LoginException, CustomerException {
		// TODO Auto-generated method stub
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("You're not logged in");
		Optional<Customer> customer = customerRepo.findById(cus.getUserId());
		if (customer.isEmpty())
			throw new CustomerException("Customer not found");
		
		Customer us = customer.get();
		Optional<Package> pack = packageRepo.findById(feedback.getPackageId()) ;
		pack.get().getFeedbacks().add(feedback);
		feedbackRepo.save(feedback);
		return new FeedbackResponse(LocalDateTime.now(), "Feedback sucessfully submitted");
	}

	@Override
	public List<Feedback> getFeedbackByPackageId(int packageid) throws FeedbackException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
