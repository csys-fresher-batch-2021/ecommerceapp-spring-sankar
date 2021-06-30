package in.sankarvinoth.ecommerce_app.util.validator;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import in.sankarvinoth.ecommerce_app.dao.UserRepository;
import in.sankarvinoth.ecommerce_app.model.User;

@Component
public class UserValidator {
@Autowired
UserRepository userRepository;
	

	
	/**
	 * method to validate users login credentials already registered or not 
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean userLoginValidation(User user) {
		List<User> users=userRepository.findUserByUserNameAndPassword(user.getUserName(),user.getPassword());
		boolean isValidUser=false;
		for (User userdetails : users) {
			if (userdetails.getUserName().equals(user.getUserName())
					&&  userdetails.getPassword().equals(user.getPassword())) {
				isValidUser = true;
				break;
			}
		}
		return isValidUser;
	}
}
