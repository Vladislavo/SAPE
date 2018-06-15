package es.uji.ei102717bmr.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102717bmr.sape.model.Student;
import es.uji.ei102717bmr.sape.model.UserDetails;

@Repository
public class UserProvider implements UserDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	public UserProvider() {
		super();
	}
	
	private static final class UserMapper implements RowMapper<UserDetails> { 

	    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException { 
	    	UserDetails user = new UserDetails();
	    	user.setId(rs.getString("id"));
	    	user.setMail(rs.getString("mail"));
	    	user.setPassword(rs.getString("password"));
	    	user.setRole(rs.getString("role"));
	        
	        return user;
	    }
	}
	
	@Override
	public UserDetails loadUserByMail(String mail, String password) {
		UserDetails user = (UserDetails) jdbcTemplate.query("select * from Users where user.mail=?;"
				, new Object[]{mail}, new UserMapper());
	    if (user == null)
	        return null; // User not found
	    // Password
	    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	    if (passwordEncryptor.checkPassword(password, user.getPassword())) {
	        // Field password should be deleted in a secure way before return
	    	user.setPassword("");
	    	return user;
	    } else {
	        return null; // bad login!
	    }
	}

	@Override
	public Collection<UserDetails> listAllUsers() {
		return this.jdbcTemplate.query("select * from Users;", new UserMapper());
	}
}
