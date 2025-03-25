package com.infy.RewardPoinntSystem.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	Long fieldValue;
	String stringValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String stringValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,stringValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.stringValue = stringValue;
	}
	
}