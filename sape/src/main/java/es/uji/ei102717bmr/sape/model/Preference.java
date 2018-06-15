package es.uji.ei102717bmr.sape.model;

import java.util.Date;

public class Preference {
	private int preferenceOrder;
	private Date lastChangeDate;
	
	private String student_nif;
	private long projectOffer_id;
	
	public Preference(){
		super();
	}
	
	public int getOrder() {
		return preferenceOrder;
	}
	public void setOrder(int order) {
		this.preferenceOrder = order;
	}
	public Date getLastChangeDate() {
		return lastChangeDate;
	}
	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public int getPreferenceOrder() {
		return preferenceOrder;
	}

	public void setPreferenceOrder(int preferenceOrder) {
		this.preferenceOrder = preferenceOrder;
	}

	public String getStudent_nif() {
		return student_nif;
	}

	public void setStudent_nif(String student_nif) {
		this.student_nif = student_nif;
	}

	public long getProjectOffer_id() {
		return projectOffer_id;
	}

	public void setProjectOffer_id(long projectOffer_id) {
		this.projectOffer_id = projectOffer_id;
	}

	@Override
	public String toString() {
		return "Preference [preferenceOrder=" + preferenceOrder + ", lastChangeDate=" + lastChangeDate
				+ ", student_nif=" + student_nif + ", projectOffer_id=" + projectOffer_id + "]";
	}
	
}
