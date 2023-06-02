package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException lx, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails(lx.getMessage(), req.getDescription(false), LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> GenericExceptionHandler(Exception ex, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> NohandlerFoundExceptionHandler(NoHandlerFoundException nf, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails(nf.getMessage(), req.getDescription(false), LocalDateTime.now());

		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		return rs;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> MANVExceptionHandler(MethodArgumentNotValidException mv) {

		MyErrorDetails err = new MyErrorDetails("Validation Error",
				mv.getBindingResult().getFieldError().getDefaultMessage(), LocalDateTime.now());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(BookingException.class)
	public ResponseEntity<MyErrorDetails> BookingsExceptionHandler(BookingException bx, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails(bx.getMessage(), req.getDescription(false), LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(RouteException.class)
	public ResponseEntity<MyErrorDetails> RouteExceptionHandler(RouteException rx, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails(rx.getMessage(), req.getDescription(false), LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(BusException.class)
	public ResponseEntity<MyErrorDetails> BusExceptionHandler(BusException bx, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails(bx.getMessage(), req.getDescription(false), LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	

}
