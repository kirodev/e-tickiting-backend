package com.exam.eticket.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.exam.eticket.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exam.eticket.domain.Ticket;
import com.exam.eticket.dtos.TicketDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/tickets")
public class TicketResource {

	@Autowired
	private TicketService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> findById(@PathVariable Integer id) {
		Ticket obj  = service.findById(id);
		return ResponseEntity.ok().body(new TicketDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TicketDTO>> findAll() {
		List<Ticket> list = service.findAll();
		List<TicketDTO> listDTO = list.stream().map(obj -> new TicketDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}	
	
	@PostMapping
	public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketDTO objDTO) {
		Ticket obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> update(@PathVariable Integer id,  @Valid @RequestBody TicketDTO objDTO) {
		Ticket newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new TicketDTO(newObj));
	}
	
}
