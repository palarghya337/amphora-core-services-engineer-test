package com.amphora.familyproject.exception;

public class InvalidDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1718132234746015556L;

	public InvalidDataException() {
	}
	public InvalidDataException(String message) {
		super(message);
	}
	public InvalidDataException(String message, Throwable e) {
		super(message, e);
	}
}
