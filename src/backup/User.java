package in.sankarvinoth.ecommerce_app.model;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.ToString;
@Component
@Data
@ToString
public class User {
private String fullName;
private String userName;
private String email;
private long mobileNumber;
@JsonProperty(access =Access.WRITE_ONLY)
private String password;
private String securityQuestion;
private String securityAnswer;
private LocalDate createdDate=LocalDate.now();
private LocalDate modifiedDate=LocalDate.now();

}
