package es.uji.ei102717bmr.sape.model;

public class Internship {
	private String description;
	private int renumeration;
	private String mailContactPerson;
	
	private ProjectOffer projectOffer;
	private Company company;
	

	public Internship(){
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRenumeration() {
		return renumeration;
	}

	public void setRenumeration(int renumeration) {
		this.renumeration = renumeration;
	}

	public String getMailContactPerson() {
		return mailContactPerson;
	}

	public void setMailContactPerson(String mailContactPerson) {
		this.mailContactPerson = mailContactPerson;
	}

	public ProjectOffer getProjectOffer() {
		return projectOffer;
	}

	public void setProjectOffer(ProjectOffer projectOffer) {
		this.projectOffer = projectOffer;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString(){
		return "InternshipOffer [cif_company="+company.getCif()+", description="+description+", "
				+ "renumeration="+renumeration+", mailContactPerson="+mailContactPerson+", "
						+ "projectOfferID="+projectOffer.getId();
	}
	
}
