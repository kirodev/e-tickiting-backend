package com.exam.eticket.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priorite {

	BAS(0, "BAS"), MOYENNE(1, "MOYENNE"), HAUT(2, "HAUT");
	
	private Integer code;
	private String description;
	
	public static Priorite toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Priorite x : Priorite.values()) {
			if(cod.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Priorite inválida");
	}
	
}
