package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.CurrentUserSession;

@Repository
public interface SessionRepository extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findBySessionId(String sessionId);
	
}
