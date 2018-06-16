package es.uji.ei102717bmr.sape.model;

public class Internship {
	private long id;
	private String description;
	private String remuneration;
	private String mailcontactperson;
	private String cif_Company;
	

	public Internship(){
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(String remuneration) {
		this.remuneration = remuneration;
	}

	public String getMailContactPerson() {
		return mailcontactperson;
	}

	public void setMailContactPerson(String mailContactPerson) {
		this.mailcontactperson = mailContactPerson;
	}
	
	public String getCif_Company() {
		return cif_Company;
	}

	public void setCif_Company(String cif_Company) {
		this.cif_Company = cif_Company;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		return "InternshipOffer [id="+id+", cif_company="+cif_Company+", remuneration="+remuneration+", "
				+ "mailContactPerson="+mailcontactperson+", description="+description + "]";
	}
	
}
