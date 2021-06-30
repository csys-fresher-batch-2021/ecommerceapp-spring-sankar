package in.sankarvinoth.ecommerce_app.service;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sankarvinoth.ecommerce_app.commons.Message;
import in.sankarvinoth.ecommerce_app.dao.UserRepository;
import in.sankarvinoth.ecommerce_app.exceptions.ServiceException;
import in.sankarvinoth.ecommerce_app.model.User;
import in.sankarvinoth.ecommerce_app.util.validator.UserValidator;


@Service
public class UserService {
   @Autowired
   UserValidator uservalidator;
   @Autowired
   Message message;
   @Autowired
   UserRepository userRepository;
   
   
   
   public void addUserService(User user) {
	  
	       List<User> users=userRepository.findUserByMobileNumberAndEmail(user.getMobileNumber(),user.getEmail());
	       if(users.isEmpty()) {
	    	   userRepository.saveUser(user);
	    	   message.setInfoMessage("Successfully Registered");
	       }
	       else {
	    	   throw new ServiceException("User already exists");
	       }
   }

	/**
	 * method to check both inputs and user credentials true
	 * 
	 * @param user
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ServiceException 
	 */
	public boolean userLogin(User user) {
		boolean isValidLogin = false;
		boolean isValidUser =uservalidator.userLoginValidation(user);
		if (isValidUser) {
			message.setInfoMessage("Succesfully LoggedIn");
		}
		else {
			throw new ServiceException("Invalid Inputs");
		}
		return isValidLogin;
	}
}
