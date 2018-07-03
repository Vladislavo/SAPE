package es.uji.ei102717bmr.sape.services;

import java.util.List;
import java.util.Map;

import es.uji.ei102717bmr.sape.model.*;

public interface SapeServices {
    List<ProjectOffer> projectOffersNotAssigned();
    List<Assignment> assignmentsNotAssigned();
    List<Student> studentsWithoutProjectAssigned();
    
    List<Tutor> getTutors();
    List<ProjectOffer> getProjectOffers();
    List<Student> getStudents();
    List<Internship> getInternships();
    
    Map<String, String> projectIdCompanyNameMatches();
    Map<String, String> internshipIdMailContactPerson();
    Map<String, List<Preference>> getStudentsPreferences();
	Map<String, String> getProjectOffersToTitles();
	Map<String, Assignment> studentAssignProjects();
	Map<String, Boolean> isProjectAssigned();
	Map<String, String> studentNifToProjectAssigned();
	Map<String, String> projectIdCompanyCif();
	Map<String, String> studentNifToName();
	Map<String, String> tutorMailToName();
	
	List<ProjectOffer> getProjectOffersByState(int state);
	
	Map<String, String> projectIdCompanyContactPerson();
}
