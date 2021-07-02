package in.sankarvinoth.ecommerce_app.commons;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



import in.sankarvinoth.ecommerce_app.exceptions.ServiceException;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {
@Autowired
Message message;
@Autowired
APIResponse apiResponse;
	
	private static final long serialVersionUID = 1L;

	/**
	 * method to handle service exception, when exception occurs that exception message is 
	 * set as errorMessage in message class .
	 * @param e
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler
	public ResponseEntity usernameExistException(ServiceException e) {
		message.setErrorMessage(e.getMessage());
		apiResponse.setMessage(message.getErrorMessage());
		apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return  ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	/**
	 * method to handle the validation exceptions 
	 * @param e
	 * @return
	 */
	    @SuppressWarnings("rawtypes")
		@ExceptionHandler(MethodArgumentNotValidException.class)
	    ResponseEntity  handleConstraintViolationException2(MethodArgumentNotValidException e) {
	    	List<FieldError> fieldErrors = e.getFieldErrors();
	    	Map<String,String> messages = new LinkedHashMap<>();
	    	for(FieldError field : fieldErrors) {
	    		messages.put( field.getField(), field.getDefaultMessage());
	    	}
	        message.setErrors(messages);
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage(messages);
			return  ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	     
	      
	    }
	
}
