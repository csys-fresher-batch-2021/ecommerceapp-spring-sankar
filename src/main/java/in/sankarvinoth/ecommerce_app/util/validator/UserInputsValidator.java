package in.sankarvinoth.ecommerce_app.util.validator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import in.sankarvinoth.ecommerce_app.dao.UserRepository;
import in.sankarvinoth.ecommerce_app.model.User;
@Component
public class UserInputsValidator {
	@Autowired
	UserRepository userRepository;
		
	public boolean securityQuestionAndAnswerValidator(Long mobileNumber,String securityQuestion,String securityAnswer) {
    boolean  isValidSecurityCredentials=false;	
    List<User> matchedUsers=userRepository.findUserByMobileNumber(mobileNumber);
    for (User userdetails :matchedUsers ) {
		if (userdetails.getSecurityQuestion().equals(securityQuestion)
				&&  userdetails.getPassword().equals(securityAnswer)) {
			isValidSecurityCredentials = true;
			break;
		}
	}
	return isValidSecurityCredentials;
}
}
