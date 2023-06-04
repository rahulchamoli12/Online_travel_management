package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.TicketDetails;

public interface TicketRepository extends JpaRepository<TicketDetails, Integer> {

}
