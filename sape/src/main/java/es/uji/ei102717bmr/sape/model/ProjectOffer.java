package es.uji.ei102717bmr.sape.model;

import java.util.Date;
import java.util.List;

public class ProjectOffer {
	private long id;
	private long id_internship;

	private String title;
	private String itinerary;
	private String tasks;
	private String objectives;
	private long state;
	private String mailContactPerson_InternshipOffer;
	private Date startDate;
	private Date lastChangeDate;
	
	//private Student student;
	//private List<Review> reviews;
	//private List<Preference> preferences;
	//private List<Assignment> assignments;
	
	public ProjectOffer(){
		super();
	}
	
	public long getId_internship() {
		return id_internship;
	}

	public void setId_internship(long id_Internship) {
		this.id_internship = id_Internship;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTasks() {
		return tasks;
	}

	public void setTasks(String tasks) {
		this.tasks = tasks;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getMailContactPerson_InternshipOffer() {
		return mailContactPerson_InternshipOffer;
	}

	public void setMailContactPerson_InternshipOffer(String mailContactPerson_InternshipOffer) {
		this.mailContactPerson_InternshipOffer = mailContactPerson_InternshipOffer;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	@Override
	public String toString() {
		return "ProjectOffer [id=" + id + ", id_Internship=" + id_internship + ", title=" + title + ", itinerary="
				+ itinerary + ", tasks=" + tasks + ", objectives=" + objectives + ", state=" + state
				+ ", mailContactPerson_InternshipOffer=" + mailContactPerson_InternshipOffer + ", startDate="
				+ startDate + ", lastChangeDate=" + lastChangeDate + "]";
	}
		
}
