package in.sankarvinoth.ecommerce_app.dto;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;



import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class UserDTO {

@NotNull(message="fullname cannot be null")
@Size(min = 5, max=30, message = "fullName must be between 5 and 30 characters")
 private String fullName;
@NotNull(message="username cannot be null")

private String userName;

private Long mobileNumber;
@Email(message="invalid email id")
private String email;
private String password;
private String confirmPassword;
private String securityQuestion;
private String securityAnswer;
}
