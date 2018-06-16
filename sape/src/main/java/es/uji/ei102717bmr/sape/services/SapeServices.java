package es.uji.ei102717bmr.sape.services;

import java.util.List;
import java.util.Map;

import es.uji.ei102717bmr.sape.model.*;

public interface SapeServices {
    public List<ProjectOffer> projectOffersNotAssigned();
    public List<Assignment> assignmentsNotAssigned();
    public List<Student> studentsWithoutProjectAssigned();
    
    public List<Tutor> getTutors();
    public List<ProjectOffer> getProjectOffers();
    public List<Student> getStudents();
    public List<Internship> getInternships();
    
    Map<String, String> projectIdCompanyNameMatches();
    Map<String, String> internshipIdMailContactPerson();
    Map<String, List<Preference>> getStudentsPreferences();
	Map<String, String> getProjectOffersToTitles();
	Map<String, Assignment> studentAssignProjects();
	Map<String, Boolean> isProjectAssigned();
	Map<String, String> studentNifToProjectAssigned();
}
