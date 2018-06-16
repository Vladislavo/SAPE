package es.uji.ei102717bmr.sape.services;

import java.util.List;
import java.util.Map;

import es.uji.ei102717bmr.sape.model.*;

public interface SapeServices {
    List<ProjectOffer> projectOffersNotAssigned();
    List<Assignment> assignmentsNotAssigned();
    List<Student> studentsWithoutProjectAssigned();
    
<<<<<<< HEAD
    public List<Tutor> getTutors();
    public List<ProjectOffer> getProjectOffers();
    public List<Student> getStudents();
    public List<Internship> getInternships();
=======
    List<Tutor> getTutors();
    List<ProjectOffer> getProjectOffers();
    List<Student> getStudents();
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
    
    Map<String, String> projectIdCompanyNameMatches();
    Map<String, String> internshipIdMailContactPerson();
    Map<String, List<Preference>> getStudentsPreferences();
	Map<String, String> getProjectOffersToTitles();
	Map<String, Assignment> studentAssignProjects();
	Map<String, Boolean> isProjectAssigned();
	Map<String, String> studentNifToProjectAssigned();
}
