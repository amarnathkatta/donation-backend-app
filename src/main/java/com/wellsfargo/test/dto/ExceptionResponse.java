package com.wellsfargo.test.dto;

import lombok.Data;

@Data
public class ExceptionResponse {
	private String message;
	private Long timeStamp;
	private Integer status;

	public ExceptionResponse() {
		// No message to be set
	}

	public ExceptionResponse(String message, Long timeStamp, Integer status) {
		super();
		this.message = message;
		this.timeStamp = timeStamp;
		this.status = status;
	}

}
