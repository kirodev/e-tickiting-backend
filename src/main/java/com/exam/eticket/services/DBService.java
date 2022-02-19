package com.exam.eticket.services;

import java.util.Arrays;

import com.exam.eticket.repositories.ClienteRepository;
import com.exam.eticket.repositories.TechnicienRepository;
import com.exam.eticket.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.eticket.domain.Ticket;
import com.exam.eticket.domain.Cliente;
import com.exam.eticket.domain.Technicien;
import com.exam.eticket.domain.enums.Profile;
import com.exam.eticket.domain.enums.Priorite;
import com.exam.eticket.domain.enums.Status;

@Service
public class DBService {

	@Autowired
	private TechnicienRepository technicienRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired 
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		Technicien tec1 = new Technicien(null, "Karim", "98498498498", "exam233@gmail.com", encoder.encode("12345"));
		tec1.addProfile(Profile.ADMIN);
		
		Technicien tec2 = new Technicien(null, "omar", "64646479879", "aitogram@gmail.com", encoder.encode("54321"));
		tec1.addProfile(Profile.TECNICO);
		
		
		Cliente cli1 = new Cliente(null, "bell", "95489878", "gates@hotmail.com", encoder.encode("54321"));
		cli1.addProfile(Profile.CLIENTE);
		Ticket ch1 = new Ticket(null, Priorite.HAUT, Status.ENCOURS, "Ticket 01", "bell Ticket", tec1, cli1);

		
		Cliente cli2 = new Cliente(null, "steve", "5747797", "stevejobs@protonmail.com", encoder.encode("54321"));
		Ticket ch2 = new Ticket(null, Priorite.MOYENNE, Status.OUVERT, "Ticket 02", "steve Ticket", tec2, cli2);
		
		technicienRepository.saveAll(Arrays.asList(tec1, tec2));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		ticketRepository.saveAll(Arrays.asList(ch1, ch2));	
	}
	
}
