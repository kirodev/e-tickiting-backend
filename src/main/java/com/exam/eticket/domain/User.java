package com.exam.eticket.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.exam.eticket.domain.enums.Profile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@SuppressWarnings("serial")
public abstract class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String nome;
	
	@Column(unique = true)
	protected String tel;
	
	@Column(unique = true)
	protected String email;
	protected String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PROFILE")
	protected Set<Integer> profiles = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dateCreation = LocalDate.now();
	
	public User() {
		super();
		addProfile(Profile.CLIENTE);
	}
	
	public User(Integer id, String nome, String tel, String email, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.tel = tel;
		this.email = email;
		this.password = password;
		addProfile(Profile.CLIENTE);
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		this.profiles.add(profile.getCode());
	}
}
