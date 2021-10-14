package com.qa.dfelibrary1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No library found with that id")

public class LibraryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7732266143612425548L;

	public LibraryNotFoundException() {
		super();
		
	}

	public LibraryNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public LibraryNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LibraryNotFoundException(String message) {
		super(message);
		
	}

	public LibraryNotFoundException(Throwable cause) {
		super(cause);
		
	}

 
}
