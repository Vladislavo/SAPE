package es.uji.ei102717bmr.sape.services;

import java.util.List;

import es.uji.ei102717bmr.sape.model.*;

public interface SapeServices {
	public List<Company> getCompanyList();
    public List<Internship> getInernshipList();
    public List<Assignment> getAssignmentList();
    public List<Preference> getPreferenceList();
    public List<ProjectOffer> getProjectOfferList();
    public List<Review> getReviewList();
    public List<Student> getStudentList();
    public List<Tutor> getTutorList();
}
