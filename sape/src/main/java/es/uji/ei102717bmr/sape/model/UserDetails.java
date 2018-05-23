package es.uji.ei102717bmr.sape.model;

public class UserDetails {
	String id;
    String mail;
    String password; 
    String role;
    

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
       return password; 
    }

    public void setPassword(String password) {
       this.password = password;
    }
}