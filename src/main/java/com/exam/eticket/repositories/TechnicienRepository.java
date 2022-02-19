package com.exam.eticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.eticket.domain.Technicien;

public interface TechnicienRepository extends JpaRepository<Technicien, Integer> {

}
