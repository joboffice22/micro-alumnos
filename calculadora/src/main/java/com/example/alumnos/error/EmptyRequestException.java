package com.example.alumnos.error;

public class EmptyRequestException extends RuntimeException{

	public EmptyRequestException (String message) {
		super(message);
	}

}
