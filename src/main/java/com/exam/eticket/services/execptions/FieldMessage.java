package com.exam.eticket.services.execptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class FieldMessage implements Serializable {

	private String fieldName;
	private String message;
	
}
