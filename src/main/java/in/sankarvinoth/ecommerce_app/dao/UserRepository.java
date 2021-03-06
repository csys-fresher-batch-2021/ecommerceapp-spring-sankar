package in.sankarvinoth.ecommerce_app.dao;



import java.util.List;

import in.sankarvinoth.ecommerce_app.model.User;

public interface UserRepository{
	public int saveUser(User users);
	public List<User> findUserByUserNameAndPassword(String userName,String password);
	public List<User> findUserByMobileNumberAndEmail(Long mobileNumber,String email);
	public List<User> findUserByMobileNumber(Long mobileNumber);
	public int UpdateUserCredentials(Long mobileNumber,String password);
}
