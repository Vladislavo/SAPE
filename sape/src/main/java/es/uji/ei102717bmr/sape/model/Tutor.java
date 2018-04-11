package es.uji.ei102717bmr.sape.model;

import java.util.List;

public class Tutor {
	private String name;
	private String mail;
	private String office;
	private String telephone;
	
	List<Assignment> assignmentes;
	
	public Tutor(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Assignment> getAssignmentes() {
		return assignmentes;
	}

	public void setAssignmentes(List<Assignment> assignmentes) {
		this.assignmentes = assignmentes;
	}

	@Override
	public String toString() {
		return "Tutor [name=" + name + ", email=" + mail + ", office=" + office + ", telephone=" + telephone
				+ ", assignmentes=" + assignmentes + "]";
	}
	
}
