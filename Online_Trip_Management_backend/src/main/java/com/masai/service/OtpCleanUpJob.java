package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.masai.entity.OTP;
import com.masai.repository.OtpRepository;


/**
 * 
 * @author Aman_Maurya
 *
 *This class is used to Delete OTP from record from after 10 minute of registered time
 */
@Component
public class OtpCleanUpJob {

	
    @Autowired
    private OtpRepository otpRepository;

    @Scheduled(fixedDelay = 10 * 60 * 1000) // run after every 10 minutes
    public void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        List<OTP> expiredTokens = otpRepository.findByCreatedAtBefore(now.minusMinutes(10));
        // delete all expired otp tokens from database
        otpRepository.deleteAll(expiredTokens);
    }
}

