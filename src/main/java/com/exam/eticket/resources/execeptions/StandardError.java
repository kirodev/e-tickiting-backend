package com.exam.eticket.resources.execeptions;

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
public class StandardError implements Serializable {
	
	private Long timestamp;
	private Integer status;
	
	private String error;
	private String message;
	private String path;
	
}
