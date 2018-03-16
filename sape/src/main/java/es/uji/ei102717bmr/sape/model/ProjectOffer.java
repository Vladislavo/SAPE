package es.uji.ei102717bmr.sape.model;

import java.sql.Date;
import java.util.List;

public class ProjectOffer {
	private int id;
	private String title;
	private String itinerary;
	private String tasks;
	private String objectives;
	private String state;
	private String mailContactPerson_InternshipOffer;
	private Date startDate;
	private Date lastChangeDate;
	
	private Internship internship;
	private Student student;
	private List<Review> reviews;
	private List<Preference> preferences;
	private List<Assignment> assignments;
	
	public ProjectOffer(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMailContactPerson_InternshipOffer() {
		return mailContactPerson_InternshipOffer;
	}

	public void setMailContactPerson_InternshipOffer(String mailContactPerson_InternshipOffer) {
		this.mailContactPerson_InternshipOffer = mailContactPerson_InternshipOffer;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}
	
	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignment) {
		this.assignments = assignment;
	}

	@Override
	public String toString() {
		return "ProjectOffer [id=" + id + ", title=" + title + ", itinerary=" + itinerary + ", tasks=" + tasks
				+ ", objectives=" + objectives + ", state=" + state + ", mailContactPerson_InternshipOffer="
				+ mailContactPerson_InternshipOffer + ", startDate=" + startDate + ", lastChangeDate=" + lastChangeDate
				+ ", studentNIF=" + student.getNIF() + ", companyCIF=" + internship.getCompany().getCif()
				+ ", reviews=" + reviews + ", preferences" + preferences + ", assignmentes=" 
				+ assignments + "]";
	}
		
}
