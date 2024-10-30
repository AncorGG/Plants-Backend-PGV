package com.ancor.exceptions;

public class PlantNotFoundException extends RuntimeException{
	public PlantNotFoundException(String message) {
		super(message);
	}
}
