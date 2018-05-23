package es.uji.ei102717bmr.sape.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import es.uji.ei102717bmr.sape.model.ProjectOffer;
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
	public List<ProjectOffer> projectOffersNotAssigned() {
		List<Assignment> assignments = assignmentDao.getAssignments();
		List<ProjectOffer> projectOffers = projectOfferDao.getProjectOffers();
		
		Map<Long, Assignment> mapAssignments = new HashMap<>();
		
		assignments.stream()
			.forEach(assignment -> mapAssignments.put(assignment.getId_projectoffer(), assignment));
		
		return projectOffers.stream()
				.filter(projectOffer -> (mapAssignments.get(projectOffer.getId()) == null || 
						!mapAssignments.get(projectOffer.getId()).getState().equals("Assigned")))
				.collect(Collectors.toList());
	}

	@Override
	public List<Assignment> assignmentsNotAssigned() {
		return assignmentDao.getAssignments().stream()
				.filter(assignment -> !assignment.getState().equals("Assigned"))
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> studentsWithoutProjectAssigned() {
		List<Assignment> assignments = assignmentDao.getAssignments();
		List<Student> students = studentDao.getStudents();
		
		Map<String, Assignment> mapAssignments = new HashMap<>();
		
		assignments.stream()
			.forEach(assignment -> mapAssignments.put(assignment.getNif_student(), assignment));
		
		return students.stream()
				.filter(student -> (mapAssignments.get(student.getNif()) == null) || 
						!mapAssignments.get(student.getNif()).getState().equals("Assigned"))
				.collect(Collectors.toList());
	}

	@Override
	public List<Tutor> getTutors() {
		return tutorDao.getTutors();
	}

	@Override
	public List<ProjectOffer> getProjectOffers() {
		return projectOfferDao.getProjectOffers();
	}

	@Override
	public List<Student> getStudents() {
		return studentDao.getStudents();
	}
}
