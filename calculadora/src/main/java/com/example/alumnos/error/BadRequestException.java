package com.example.alumnos.error;

public class BadRequestException extends RuntimeException {
	
	public BadRequestException (String message) {
		super(message);
	}
}
