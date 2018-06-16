package es.uji.ei102717bmr.sape.model;

import java.util.Date;

public class Assignment {
	private boolean state;
	private Date creationDate;
	private Date approvalDate;
	private Date rejectDate;
	
	private long id_projectoffer;
	private String nif_student;
	private String mail_tutor;
	//private ProjectOffer projectOffer;
	//private Tutor tutor;
	//private Student student;
	
	public Assignment(){
		super();
		creationDate = new Date();
		state = false;
	}
	
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public Date getRejectDate() {
		return rejectDate;
	}
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}

	public long getId_projectoffer() {
		return id_projectoffer;
	}

	public void setId_projectoffer(long id_projectoffer) {
		this.id_projectoffer = id_projectoffer;
	}

	public String getNif_student() {
		return nif_student;
	}

	public void setNif_student(String nif_student) {
		this.nif_student = nif_student;
	}

	public String getMail_tutor() {
		return mail_tutor;
	}

	public void setMail_tutor(String mail_tutor) {
		this.mail_tutor = mail_tutor;
	}

	@Override
	public String toString() {
		return "Assignment [state=" + state + ", creationDate=" + creationDate + ", approvalDate=" + approvalDate
				+ ", rejectDate=" + rejectDate + ", id_projectoffer=" + id_projectoffer + ", nif_student=" + nif_student
				+ ", mail_tutor=" + mail_tutor + "]";
	}
	
}
