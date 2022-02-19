package com.exam.eticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.eticket.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
