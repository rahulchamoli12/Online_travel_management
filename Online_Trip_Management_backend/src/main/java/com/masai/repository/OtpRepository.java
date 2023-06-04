package com.masai.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.OTP;

public interface OtpRepository extends JpaRepository<OTP, Integer>{
	
	Optional<OTP> findByEmail(String email);

	List<OTP> findByCreatedAtBefore(LocalDateTime dateTime);
}
