package com.exam.eticket.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.exam.eticket.repositories.TechnicienRepository;
import com.exam.eticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.eticket.domain.User;
import com.exam.eticket.domain.Technicien;
import com.exam.eticket.dtos.TechnicienDTO;
import com.exam.eticket.services.execptions.DataIntegrityViolationException;
import com.exam.eticket.services.execptions.ObjectNotFoundException;

@Service
public class TechnicienService {

	@Autowired
	private TechnicienRepository technicienRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private BCryptPasswordEncoder encoder;
	
	public Technicien findById(Integer id) {
		Optional<Technicien> obj = technicienRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objet non trouvé! ID: " + id));
	}

	public List<Technicien> findAll() {
		return technicienRepository.findAll(); 
	}

	public Technicien create(TechnicienDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validaPorTelEEmail(objDTO);
		Technicien newObj = new Technicien(objDTO);
		return technicienRepository.save(newObj);
	}

	public Technicien update(Integer id, @Valid TechnicienDTO objDTO) {
		objDTO.setId(id);
		Technicien oldObj = findById(id);
		validaPorTelEEmail(objDTO);
		oldObj = new Technicien(objDTO);
		return technicienRepository.save(oldObj);
	}
	

	public void delete(Integer id) {
		Technicien obj = findById(id);
		if(obj.getTickets().size() > 0) {
			throw new DataIntegrityViolationException("ne peut pas être supprimé.");
		}
		technicienRepository.deleteById(id);
	}
	
	private void validaPorTelEEmail(TechnicienDTO objDTO) {	
		Optional<User> obj = userRepository.findByTel(objDTO.getTel());
		
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("TEL déjà enregistré.");
		}
		
		obj = userRepository.findByEmail(objDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail déjà enregistré.");
		}
	}
	
}
