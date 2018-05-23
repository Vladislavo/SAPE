package es.uji.ei102717bmr.sape.model;

import java.util.List;

public class Student {
	private String name;
	private String email;
	private String nif;
	private String telephone;
	private String gradeCertificate;
	private String itinerary;
	
	private List<Assignment> assignments;
	private List<Preference> preferences;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getGradeCertificate() {
		return gradeCertificate;
	}
	public void setGradeCertificate(String gradeCertificate) {
		this.gradeCertificate = gradeCertificate;
	}
	public String getItinerary() {
		return itinerary;
	}
	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	public List<Preference> getPreferences() {
		return preferences;
	}
	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", email=" + email + ", nif=" + nif + ", telephone=" + telephone
				+ ", gradeCertificate=" + gradeCertificate + ", itinerary=" + itinerary + ", assignments=" + assignments
				+ ", preferences=" + preferences + "]";
	}

}
