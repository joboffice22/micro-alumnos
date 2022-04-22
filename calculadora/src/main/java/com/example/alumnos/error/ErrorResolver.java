package com.example.alumnos.error;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
@RestControllerAdvice
public class ErrorResolver {
	ErrorResponse error=new ErrorResponse();
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ErrorResponse resolveBadRequestException(HttpServletRequest req, BadRequestException ex) {
		//ErrorResponse error=new ErrorResponse();
		error.setType("Error");
		error.setCode(HttpStatus.BAD_REQUEST.toString());
		error.setDetails(ex.getMessage());
		return error;
	}
	
	@ExceptionHandler(EmptyRequestException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ErrorResponse EmptyBadRequestException(HttpServletRequest req, EmptyRequestException exEm) {
		//ErrorResponse error=new ErrorResponse();
		error.setType("Error");
		error.setCode(HttpStatus.NOT_FOUND.toString());
		error.setDetails(exEm.getMessage());
		return error;
	}
	
	@ExceptionHandler(ValidDivisorException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ErrorResponse ValidDivisorException(HttpServletRequest req, ValidDivisorException exDiv) {
		//ErrorResponse error=new ErrorResponse();
		error.setType("Error");
		error.setCode(HttpStatus.NOT_FOUND.toString());
		error.setDetails(exDiv.getMessage());
		return error;
	}

}
