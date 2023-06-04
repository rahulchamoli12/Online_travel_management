package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.FeedbackResponse;
import com.masai.entity.Feedback;
import com.masai.exception.CustomerException;
import com.masai.exception.FeedbackException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;
import com.masai.service.FeedbackService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController("/onlinetripfeedback")
@CrossOrigin("*")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/addFeedback/{sessionId}/{packageId}")
	public ResponseEntity<FeedbackResponse> addFeedback(@Valid @RequestBody Feedback feedback, @PathVariable String sessionId, @PathVariable Integer packageId) throws LoginException, CustomerException, FeedbackException{
		FeedbackResponse feed = feedbackService.addFeedback(sessionId, feedback, packageId);
		return new ResponseEntity<FeedbackResponse>(feed, HttpStatus.OK);
	}
	
	@GetMapping("/feedbacklist/{sessionId}/{packageId}")
	public ResponseEntity<List<Feedback>> getFeedbackByPackageId(@PathVariable String sessionId, @PathVariable int packageId) throws LoginException, PackageException, FeedbackException{
		List<Feedback> feed = feedbackService.getFeedbackByPackageId(sessionId, packageId);
		return new ResponseEntity<List<Feedback>>(feed, HttpStatus.OK);
	}
	
	
}
