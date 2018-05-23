package es.uji.ei102717bmr.sape.dao;

import java.util.Collection;

import es.uji.ei102717bmr.sape.model.UserDetails;

public interface UserDAO {
    UserDetails loadUserByMail(String email, String password); 
    Collection<UserDetails> listAllUsers();
}