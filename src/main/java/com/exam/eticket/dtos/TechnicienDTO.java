package com.exam.eticket.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.exam.eticket.domain.Technicien;
import com.exam.eticket.domain.enums.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class TechnicienDTO implements Serializable {

	protected Integer id;
	
	@NotNull(message = "Nom champ obligatoire.")
	protected String nome;
	
	@NotNull(message = "TEL champ obligatoire.")
	protected String tel;
	
	@NotNull(message = "EMAIL champ obligatoire.")
	protected String email;
	
	@NotNull(message = "PASSWORD champ obligatoire.")
	protected String password;
	protected Set<Integer> profiles = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dateCreation = LocalDate.now();
	
	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		this.profiles.add(profile.getCode());
	}
	
	public TechnicienDTO() {
		super();
		addProfile(Profile.CLIENTE);
	}

	public TechnicienDTO(Technicien obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.tel = obj.getTel();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profiles = obj.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		this.dateCreation = obj.getDateCreation();
		addProfile(Profile.CLIENTE);
	}

}
