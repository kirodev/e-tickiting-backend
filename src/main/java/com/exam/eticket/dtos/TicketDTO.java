package com.exam.eticket.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.exam.eticket.domain.Ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class TicketDTO implements Serializable {

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOuverture = LocalDate.now();

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateFin;

	@NotNull(message = "Le champ PRIORITE est obligatoire")
	private Integer priorite;
	
	@NotNull(message = "Le champ STATUS est obligatoire")
	private Integer status;
	
	@NotNull(message = "Le champ TITLE est obligatoire")
	private String titre;
	
	@NotNull(message = "Le champ COMMONTAIRE est obligatoire")
	private String commentaires;

	private String nomeTechnicien;
	private String nomeCliente;
	
	@NotNull(message = "Le champ TECHNICIEN est obligatoire")
	private Integer technicien;
	
	@NotNull(message = "Le champ CLIENTE est obligatoire")
	private Integer cliente;
	

	public TicketDTO(Ticket obj) {
		this.id = obj.getId();
		this.dateOuverture = obj.getDateOuverture();
		this.dateFin = obj.getDateFin();
		this.priorite = obj.getPriorite().getCode();
		this.status = obj.getStatus().getCode();
		this.titre = obj.getTitre();
		this.commentaires = obj.getCommentaires();
		this.technicien = obj.getTechnicien().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeCliente = obj.getCliente().getNome();
		this.nomeTechnicien = obj.getTechnicien().getNome();
	}
	
	public TicketDTO() {
		super();
	}
	
}
