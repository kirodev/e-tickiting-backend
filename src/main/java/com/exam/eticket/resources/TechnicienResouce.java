package com.exam.eticket.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exam.eticket.domain.Technicien;
import com.exam.eticket.dtos.TechnicienDTO;
import com.exam.eticket.services.TechnicienService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/techniciens")
public class TechnicienResouce {

	@Autowired
	private TechnicienService service;
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<TechnicienDTO> findById(@PathVariable Integer id) {
		Technicien obj = service.findById(id);
		return ResponseEntity.ok().body(new TechnicienDTO(obj)); 
	}
	
	@GetMapping
	public ResponseEntity<List<TechnicienDTO>> findAll() {
		List<Technicien> list = service.findAll();
		List<TechnicienDTO> listDTO = list.stream().map(obj -> new TechnicienDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TechnicienDTO> create(@Valid @RequestBody TechnicienDTO objDTO) {
		Technicien newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<TechnicienDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicienDTO objDTO) {
		Technicien obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new TechnicienDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TechnicienDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
