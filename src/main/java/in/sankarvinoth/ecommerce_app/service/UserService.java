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
   
   
   /**
    * method to add new user to the user table 
    * @param user
    */
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
	/**
	 * method to update password of user
	 * @param user
	 */
	public void updatePasswordService(User user) {
		List<User> matchedUsers=userRepository.findUserByMobileNumber(user.getMobileNumber());
		if(!matchedUsers.isEmpty()) {
			int rowsAffected=userRepository.UpdateUserCredentials(user.getMobileNumber(),user.getPassword());
			if(rowsAffected>0) {
				message.setInfoMessage("Updated Successfully");
			}
			else {
				throw new ServiceException("Something Went Wrong");
			}
		}
		else {
			throw new ServiceException("please Use a registered mobile Number");
		}
	}
}
