package in.sankarvinoth.ecommerce_app.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import in.sankarvinoth.ecommerce_app.model.User;

@Repository
public class UserRepositoryImp implements UserRepository{
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final String SQL1="insert into UserRegister(fullName,userName,email,mobileNumber,password,Security_Question,Security_answer,created_date,modified_date) values(?,?,?,?,?,?,?,?,?)";
    private static final String SQL2="select username,password from UserRegister where username=? and password=?";
    private static final String SQL3="select fullname,username from UserRegister where mobileNumber=? or email=?";
    public int saveUser(User users) {
		int rowsAffected=jdbcTemplate.update(SQL1,users.getFullName(),users.getUserName(),users.getEmail(),users.getMobileNumber(),users.getPassword(),users.getSecurityQuestion(),users.getSecurityAnswer(),users.getCreatedDate(),users.getModifiedDate());
		
		return rowsAffected;
	}
	
	public List<User> findUserByUserNameAndPassword(String userName,String password) {
		return jdbcTemplate.query(SQL2, BeanPropertyRowMapper.newInstance(User.class),userName,password);
		
	}
	public List<User> findUserByMobileNumberAndEmail(Long mobileNumber,String email) {
		return jdbcTemplate.query(SQL3, BeanPropertyRowMapper.newInstance(User.class),mobileNumber,email);
		
	}
	
	
}
