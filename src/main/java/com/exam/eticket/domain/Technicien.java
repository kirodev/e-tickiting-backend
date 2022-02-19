package com.exam.eticket.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.exam.eticket.domain.enums.Profile;
import com.exam.eticket.dtos.TechnicienDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@SuppressWarnings("serial")
public class Technicien extends User {

	@JsonIgnore
	@OneToMany(mappedBy = "technicien")
	private List<Ticket> tickets = new ArrayList<>();
	
	public Technicien() {
		super();
		addProfile(Profile.CLIENTE);
	}

	public Technicien(Integer id, String nome, String tel, String email, String password) {
		super(id, nome, tel, email, password);
		addProfile(Profile.CLIENTE);
	}
	
	public Technicien(TechnicienDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.tel = obj.getTel();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profiles = obj.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		this.dateCreation = obj.getDateCreation();
	}
	
}
