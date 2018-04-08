package es.uji.ei102717bmr.sape.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei102717bmr.sape.dao.CompanyDAO;
import es.uji.ei102717bmr.sape.dao.InternshipDAO;
import es.uji.ei102717bmr.sape.dao.AssignmentDAO;
import es.uji.ei102717bmr.sape.dao.PreferenceDAO;
import es.uji.ei102717bmr.sape.dao.ProjectOfferDAO;
import es.uji.ei102717bmr.sape.dao.ReviewDAO;
import es.uji.ei102717bmr.sape.dao.StudentDAO;
import es.uji.ei102717bmr.sape.dao.TutorDAO;
import es.uji.ei102717bmr.sape.model.Assignment;
import es.uji.ei102717bmr.sape.model.Company;
import es.uji.ei102717bmr.sape.model.Internship;
import es.uji.ei102717bmr.sape.model.Preference;
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.Review;
import es.uji.ei102717bmr.sape.model.Student;
import es.uji.ei102717bmr.sape.model.Tutor;

@Service
public class SapeServicesImpl implements SapeServices {

	@Autowired
	CompanyDAO companyDao;
	
	@Autowired
	InternshipDAO internshipDao;
	
	@Autowired
	AssignmentDAO assignmentDao;
	
	@Autowired
	PreferenceDAO preferenceDao;
	
	@Autowired
	ProjectOfferDAO projectOfferDao;
	
	@Autowired
	ReviewDAO reviewDao;
	
	@Autowired
	StudentDAO studentDao;
	
	@Autowired
	TutorDAO tutorDao;
	
	@Override
	public List<Company> getCompanyList() {
		return companyDao.getCompanies();
	}

	@Override
	public List<Internship> getInernshipList() {
		return internshipDao.getInternships();
	}

	@Override
	public List<Assignment> getAssignmentList() {
		return assignmentDao.getAssignments();
	}

	@Override
	public List<Preference> getPreferenceList() {
		return preferenceDao.getPreferences();
	}

	@Override
	public List<ProjectOffer> getProjectOfferList() {
		return projectOfferDao.getProjectOffers();
	}

	@Override
	public List<Review> getReviewList() {
		return reviewDao.getReviews();
	}

	@Override
	public List<Student> getStudentList() {
		return studentDao.getStudents();
	}

	@Override
	public List<Tutor> getTutorList() {
		return tutorDao.getTutors();
	}
	
}
