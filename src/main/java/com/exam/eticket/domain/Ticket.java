package com.exam.eticket.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.exam.eticket.domain.enums.Priorite;
import com.exam.eticket.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@SuppressWarnings("serial")
public class Ticket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOuverture = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateFin;

	private Priorite priorite;
	private Status status;
	
	private String titre;
	private String commentaires;
 	
	@ManyToOne
	@JoinColumn(name = "technicien_id")
	private Technicien technicien;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	

	public Ticket(Integer id, Priorite priorite, Status status, String titre, String commentaires, Technicien technicien,
				  Cliente cliente) {
		super();
		this.id = id;
		this.priorite = priorite;
		this.status = status;
		this.titre = titre;
		this.commentaires = commentaires;
		this.technicien = technicien;
		this.cliente = cliente;
	}
}
