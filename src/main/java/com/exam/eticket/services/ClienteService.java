package com.exam.eticket.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.exam.eticket.repositories.ClienteRepository;
import com.exam.eticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.eticket.domain.User;
import com.exam.eticket.domain.Cliente;
import com.exam.eticket.dtos.ClienteDTO;
import com.exam.eticket.services.execptions.DataIntegrityViolationException;
import com.exam.eticket.services.execptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository technicienRepository;
	
	@Autowired 
	private UserRepository userRepository;

	@Autowired 
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = technicienRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objet non trouvé! ID: " + id));
	}

	public List<Cliente> findAll() {
		return technicienRepository.findAll(); 
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validaPorTelEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return technicienRepository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorTelEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return technicienRepository.save(oldObj);
	}
	

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getTickets().size() > 0) {
			throw new DataIntegrityViolationException("tu peut pas supprimer");
		}
		technicienRepository.deleteById(id);
	}
	
	private void validaPorTelEEmail(ClienteDTO objDTO) {	
		Optional<User> obj = userRepository.findByTel(objDTO.getTel());
		
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("TEL déjà enregistré.");
		}
		
		obj = userRepository.findByEmail(objDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail deja enregistre.");
		}
	}
	
}
