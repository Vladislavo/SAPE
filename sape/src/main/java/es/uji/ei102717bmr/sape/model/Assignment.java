package es.uji.ei102717bmr.sape.model;

import java.util.Date;

public class Assignment {
	private String state;
	private Date creationDate;
	private Date approvalDate;
	private Date rejectDate;
	
	private ProjectOffer projectOffer;
	private Tutor tutor;
	private Student student;
	
	public Assignment(){
		super();
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
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
	public ProjectOffer getProjectOffer() {
		return projectOffer;
	}
	public void setProjectOffer(ProjectOffer projectOffer) {
		this.projectOffer = projectOffer;
	}
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Assignment [state=" + state + ", creationDate=" + creationDate + ", approvalDate=" + approvalDate
				+ ", rejectDate=" + rejectDate + ", projectOfferID=" + projectOffer.getId() + ", tutor=" + tutor
				+ "studentNIF=" + student.getNIF() + "]";
	}
	
}
