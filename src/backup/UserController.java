package in.sankarvinoth.ecommerce_app.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sankarvinoth.ecommerce_app.commons.APIResponse;
import in.sankarvinoth.ecommerce_app.commons.Message;


import in.sankarvinoth.ecommerce_app.dto.UserDTO;
import in.sankarvinoth.ecommerce_app.dto.UserLoginDTO;

import in.sankarvinoth.ecommerce_app.model.User;

import in.sankarvinoth.ecommerce_app.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	User users;
	@Autowired
	APIResponse apiResponse;
	@Autowired
	Message message;
     
	/**
	 * method to add new User to the database
	 * @param userdto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("AddUser")
	public ResponseEntity  saveUser(@Valid @RequestBody UserDTO userdto) {
		users.setFullName(userdto.getFullName());
		users.setUserName(userdto.getUserName());
		users.setMobileNumber(userdto.getMobileNumber());
		users.setEmail(userdto.getEmail());
		users.setPassword(userdto.getPassword());
		users.setSecurityQuestion(userdto.getSecurityQuestion());
		users.setSecurityAnswer(userdto.getSecurityAnswer());
		userService.addUserService(users);
		apiResponse.setData(userdto);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage(message.getInfoMessage());
		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		
	}
	
	/**
	 * method for getting all the login inputs and verification
	 * @param logindto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("UserLogin")
	public ResponseEntity verifyUser(@Valid @RequestBody UserLoginDTO logindto) {
		users.setUserName(logindto.getUserName());
		users.setPassword(logindto.getPassword());
				userService.userLogin(users);
				apiResponse.setData(logindto);
				apiResponse.setStatus(HttpStatus.OK.value());
				apiResponse.setMessage(message.getInfoMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		
	}
	
}
