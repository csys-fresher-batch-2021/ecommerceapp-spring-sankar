package in.sankarvinoth.ecommerce_app.commons;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;
@Component
@Data
@ToString
@JsonInclude(value = Include.NON_NULL)
public class APIResponse {
	
	public APIResponse() {
		super();
		this.status =0;
		this.data =null;
		this.message =null;
	}
	private int status;
	private Object data;
	private Object message;
	
}
