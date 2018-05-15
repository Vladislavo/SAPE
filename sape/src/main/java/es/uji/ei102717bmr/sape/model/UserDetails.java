package es.uji.ei102717bmr.sape.model;

public class UserDetails {
    String email;
    String password; 
    
    public String getEmail() {
        return email; 
    }

    public void setEmail(String username) {
        this.email = username; 
    }

    public String getPassword() {
       return password; 
    }

    public void setPassword(String password) {
       this.password = password;
    }
}