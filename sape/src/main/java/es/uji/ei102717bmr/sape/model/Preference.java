package es.uji.ei102717bmr.sape.model;

import java.util.Date;

public class Preference {
	private int preference_order;
	private String state;
	private Date lastChangeDate;
	
	private Student student;
	private ProjectOffer proectOffer;
	
	public Preference(){
		super();
	}
	
	public int getOrder() {
		return preference_order;
	}
	public void setOrder(int order) {
		this.preference_order = order;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getLastChangeDate() {
		return lastChangeDate;
	}
	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public ProjectOffer getProectOffer() {
		return proectOffer;
	}
	public void setProectOffer(ProjectOffer proectOffer) {
		this.proectOffer = proectOffer;
	}
	@Override
	public String toString() {
		return "Preference [order=" + preference_order + ", state=" + state + ", lastChangeDate=" + lastChangeDate + ", studentNIF="
				+ student.getNIF() + ", proectOfferID=" + proectOffer.getId() + "]";
	}
	
	
}
