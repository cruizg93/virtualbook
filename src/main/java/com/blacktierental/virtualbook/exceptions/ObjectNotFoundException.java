package com.blacktierental.virtualbook.exceptions;

public class ObjectNotFoundException extends Exception {
	private static final long serialVersionUID = 252199402525173254L;

	public ObjectNotFoundException(String message){
		super(message);
	}
}
