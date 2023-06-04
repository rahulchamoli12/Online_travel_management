package com.masai.service;

import java.util.List;

import com.masai.dto.FeedbackResponse;
import com.masai.entity.Feedback;
import com.masai.exception.CustomerException;
import com.masai.exception.FeedbackException;
import com.masai.exception.LoginException;

public interface FeedbackService {
	public FeedbackResponse addFeedback(String sessionId, Feedback feedback) throws LoginException, CustomerException, FeedbackException;
	public List<Feedback> getFeedbackByPackageId(int packageid) throws FeedbackException;
}	
