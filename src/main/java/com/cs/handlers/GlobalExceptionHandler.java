package com.cs.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import com.cs.models.GenericResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BaseException.class)
	public GenericResponse handleBaseException(BaseException e) {
		GenericResponse response = new GenericResponse();
		response.setStatus(false);
		response.setMsg(e.getMessage());
		return response;
	}

	@ExceptionHandler(value = Exception.class)
	public GenericResponse handleException(Exception e) {
		GenericResponse response = new GenericResponse();
		response.setStatus(false);
		response.setMsg(e.getMessage());
		return response;
	}
}
