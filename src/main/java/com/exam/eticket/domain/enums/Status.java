package com.exam.eticket.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

	OUVERT(0, "OUVERT"), ENCOURS(1, "ENCOURS"), FERMEE(2, "FERMEE");
	
	private Integer code;
	private String description;
	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status invalide");
	}
	
}
