package es.uji.ei102717bmr.sape.model;

import java.sql.Date;

public class Review {
	private Date creationDate;
	private String text;
	
	private ProjectOffer projectOffer;

	public ProjectOffer getProjectOffer() {
		return projectOffer;
	}

	public void setProjectOffer(ProjectOffer projectOffer) {
		this.projectOffer = projectOffer;
	}

	public Review(){
		super();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	@Override
	public String toString() {
		return "Review [creationDate=" + creationDate + ", text=" + text + ", projectOffer=" + projectOffer + "]";
	}
	
}
