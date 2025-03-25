package com.infy.RewardPoinntSystem.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHnadler {

	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandler(ResourceNotFoundException re){
		 String message = re.getMessage();
		 ApiResponce apiResponce = new ApiResponce(message,false);
		 return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String , String>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			resp.put(fieldName, defaultMessage);
			
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponce> handleDuplicateEmail(DuplicateEmailException ex) {
    	ApiResponce apiResponse = new ApiResponce(ex.getMessage(), false);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponce> handleInvalidInput(InvalidInputException ex) {
    	ApiResponce apiResponse = new ApiResponce(ex.getMessage(), false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
	
	
	
//	@ExceptionHandler(ApiException.class)
//	public ResponseEntity<ApiResponce> handleApiException(ApiException re){
//		 String message = re.getMessage();
//		 ApiResponce apiResponce = new ApiResponce(message,true);
//		 return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.BAD_REQUEST);
//	}
}
