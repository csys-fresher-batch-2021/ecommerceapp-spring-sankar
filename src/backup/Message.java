package in.sankarvinoth.ecommerce_app.commons;
import java.util.Map;

import org.springframework.stereotype.Component;



import lombok.Data;

@Component
@Data
public class Message {
private String infoMessage;
private String errorMessage;
private Map<String, String> errors;
}
