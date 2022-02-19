package com.exam.eticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.eticket.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
