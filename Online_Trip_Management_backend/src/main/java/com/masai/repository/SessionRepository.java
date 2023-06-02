package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.CurrentUserSession;

public interface SessionRepository extends JpaRepository<CurrentUserSession, Integer>{
	public CurrentUserSession findBySessionid(String id);
	
}