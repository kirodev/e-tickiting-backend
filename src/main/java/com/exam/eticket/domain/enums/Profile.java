package com.exam.eticket.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Profile {

	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");
	
	private Integer code;
	private String description;
	
	public static Profile toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Profile x : Profile.values()) {
			if(cod.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Profile inválido");
	}
	
}
