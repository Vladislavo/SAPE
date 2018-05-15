package es.uji.ei102717bmr.sape.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;

import es.uji.ei102717bmr.sape.model.UserDetails;

@Repository
public class UserProvider implements UserDAO {
	final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();
	
	public UserProvider() {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	    UserDetails userAlice = new UserDetails();
	    userAlice.setEmail("alice@uji.es");
	    userAlice.setPassword(passwordEncryptor.encryptPassword("alice"));
	    knownUsers.put("alice", userAlice);
	      
	    UserDetails userBob = new UserDetails();
	    userBob.setEmail("bob@uji.es");
	    userBob.setPassword(passwordEncryptor.encryptPassword("bob"));
	    knownUsers.put("bob", userBob);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username, String password) {
		UserDetails user = knownUsers.get(username.trim());
	    if (user == null)
	        return null; // User not found
	    // Password
	    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	    if (passwordEncryptor.checkPassword(password, user.getPassword())) {
	        // Field password should be deleted in a secure way before return 
	       return user;
	    } else {
	        return null; // bad login!
	    }
	}

	@Override
	public Collection<UserDetails> listAllUsers() {
		return knownUsers.values();
	}

}
