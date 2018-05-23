package es.uji.ei102717bmr.sape.services;

import java.util.List;

import es.uji.ei102717bmr.sape.model.*;

public interface SapeServices {
    public List<ProjectOffer> projectOffersNotAssigned();
    public List<Assignment> assignmentsNotAssigned();
    public List<Student> studentsWithoutProjectAssigned();
    
    public List<Tutor> getTutors();
    public List<ProjectOffer> getProjectOffers();
    public List<Student> getStudents();
}
