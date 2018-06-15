package es.uji.ei102717bmr.sape.model;

public class Internship {
	private long id;
	private String description;
	private String renumeration;
	private String mailContactPerson;
	
	private String cif_company;
	

	public Internship(){
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRenumeration() {
		return renumeration;
	}

	public void setRenumeration(String renumeration) {
		this.renumeration = renumeration;
	}

	public String getMailContactPerson() {
		return mailContactPerson;
	}

	public void setMailContactPerson(String mailContactPerson) {
		this.mailContactPerson = mailContactPerson;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getCif_company() {
		return cif_company;
	}

	public void setCif_company(String cif_company) {
		this.cif_company = cif_company;
	}

	@Override
	public String toString() {
		return "Internship [id=" + id + ", description=" + description + ", renumeration=" + renumeration
				+ ", mailContactPerson=" + mailContactPerson + ", cif_company="
				+ cif_company + "]";
	}
}
