package es.uji.ei102717bmr.sape.model;

public class Internship {
	private long id;
	private String description;
<<<<<<< HEAD
	private String remuneration;
	private String mailcontactperson;
	private String cif_Company;
=======
	private String renumeration;
	private String mailContactPerson;
	
	private String cif_company;
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
	

	public Internship(){
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

<<<<<<< HEAD
	public String getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(String remuneration) {
		this.remuneration = remuneration;
=======
	public String getRenumeration() {
		return renumeration;
	}

	public void setRenumeration(String renumeration) {
		this.renumeration = renumeration;
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
	}

	public String getMailContactPerson() {
		return mailcontactperson;
	}

	public void setMailContactPerson(String mailContactPerson) {
<<<<<<< HEAD
		this.mailcontactperson = mailContactPerson;
	}
	
	public String getCif_Company() {
		return cif_Company;
	}

	public void setCif_Company(String cif_Company) {
		this.cif_Company = cif_Company;
=======
		this.mailContactPerson = mailContactPerson;
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
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
<<<<<<< HEAD
	public String toString(){
		return "InternshipOffer [id="+id+", cif_company="+cif_Company+", remuneration="+remuneration+", "
				+ "mailContactPerson="+mailcontactperson+", description="+description + "]";
=======
	public String toString() {
		return "Internship [id=" + id + ", description=" + description + ", renumeration=" + renumeration
				+ ", mailContactPerson=" + mailContactPerson + ", cif_company="
				+ cif_company + "]";
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
	}
}
