package es.uji.ei102717bmr.sape.model;

import java.util.Date;

public class Review {
	private Date creationDate;
	private String text;
	private Long projectOfferId;

	public Review(){
		super();
	}
	
	public Long getProjectOfferId() {
		return projectOfferId;
	}

	public void setProjectOfferId(Long projectOfferId) {
		this.projectOfferId = projectOfferId;
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
		return "Review [creationDate=" + creationDate + ", text=" + text + ", projectOfferId=" + projectOfferId + "]";
	}
	
}
