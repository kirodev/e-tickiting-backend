package com.exam.eticket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.exam.eticket.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.eticket.domain.Ticket;
import com.exam.eticket.domain.Technicien;
import com.exam.eticket.domain.enums.Priorite;
import com.exam.eticket.domain.enums.Status;
import com.exam.eticket.domain.Cliente;
import com.exam.eticket.dtos.TicketDTO;
import com.exam.eticket.services.execptions.ObjectNotFoundException;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TechnicienService technicienService;
	
	@Autowired
	private ClienteService clienteService; 
	
	public Ticket findById(Integer id) {
		Optional<Ticket> obj = ticketRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objet non trouvé! ID: " + id));
	}

	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}
	
	public Ticket create(@Valid TicketDTO objDTO) {
		return ticketRepository.save(newTicket(objDTO));
	}
	
	public Ticket update(Integer id, @Valid TicketDTO objDTO) {
		objDTO.setId(id);
		Ticket oldObj = findById(id);
		oldObj = newTicket(objDTO);
		return ticketRepository.save(oldObj);
	}

	
	private Ticket newTicket(TicketDTO obj) {
		Technicien technicien = technicienService.findById(obj.getTechnicien());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Ticket ticket = new Ticket();
		if(obj.getId() != null) {
			ticket.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			ticket.setDateFin(LocalDate.now());
		}
		
		ticket.setTechnicien(technicien);
		ticket.setCliente(cliente);
		ticket.setPriorite(Priorite.toEnum(obj.getPriorite()));
		ticket.setStatus(Status.toEnum(obj.getStatus()));
		ticket.setTitre(obj.getTitre());
		ticket.setCommentaires(obj.getCommentaires());
		
		return ticket;
	}
	
}
