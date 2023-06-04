package com.masai.service;

import java.util.List;

import com.masai.dto.FeedbackResponse;
import com.masai.entity.Feedback;
import com.masai.exception.CustomerException;
import com.masai.exception.FeedbackException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;

public interface FeedbackService {
	public FeedbackResponse addFeedback(String sessionId, Feedback feedback) throws LoginException, CustomerException, FeedbackException;
	public List<Feedback> getFeedbackByPackageId(String sessionId, int packageid) throws LoginException, PackageException,  FeedbackException;
}	
