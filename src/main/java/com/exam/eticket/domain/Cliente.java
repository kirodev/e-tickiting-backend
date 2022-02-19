package com.exam.eticket.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.exam.eticket.domain.enums.Profile;
import com.exam.eticket.dtos.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Entity
@SuppressWarnings("serial")
public class Cliente extends User {
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Ticket> tickets = new ArrayList<>();
	
	public Cliente() {
		super();
		addProfile(Profile.CLIENTE);
	}

	public Cliente(Integer id, String nome, String tel, String email, String password) {
		super(id, nome, tel, email, password);
		addProfile(Profile.CLIENTE);
	}
	
	public Cliente(ClienteDTO obj) {
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
