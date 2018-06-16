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
import es.uji.ei102717bmr.sape.model.Company;
import es.uji.ei102717bmr.sape.model.Internship;
import es.uji.ei102717bmr.sape.model.Preference;
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
						!mapAssignments.get(projectOffer.getId()).getState()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Assignment> assignmentsNotAssigned() {
		return assignmentDao.getAssignments().stream()
				.filter(assignment -> !assignment.getState())
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
						mapAssignments.get(student.getNif()).getState())
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

	@Override
<<<<<<< HEAD
	public List<Internship> getInternships() {
		return internshipDao.getInternships();
	}
	
	@Override
=======
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
	public Map<String, String> projectIdCompanyNameMatches() {
		Map<String, String> projectCompanyMatch = new HashMap<>();
		List<Internship> internships = internshipDao.getInternships();
		List<Company> companies = companyDao.getCompanies();
		List<ProjectOffer> projectOffers = projectOfferDao.getProjectOffers();
		
		
		// internship_id -> projectOffer
		Map<Long, ProjectOffer> projectOffers_map = new HashMap<>();
		projectOffers.stream()
			.forEach(po -> projectOffers_map.put(po.getId_internship(), po));
		
		// cif_company -> projectOffer
		Map<String, ProjectOffer> internships_map = new HashMap<>();
		internships.stream()
<<<<<<< HEAD
			.forEach(i -> internships_map.put(i.getCif_Company(), projectOffers_map.get(i.getId())));
=======
			.forEach(i -> internships_map.put(i.getCif_company(), projectOffers_map.get(i.getId())));
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
		
		// cif_company -> company
		Map<String, Company> companies_map = new HashMap<>();
		companies.stream()
			.forEach(c -> companies_map.put(c.getCif(), c));
		
		// projectOffer -> company_name
		companies.stream()
			.forEach(c -> projectCompanyMatch.put(internships_map.get(c.getCif()).getId()+"", c.getName()));
		
		return projectCompanyMatch;
	}

	@Override
	public Map<String, String> internshipIdMailContactPerson() {
		Map<String, String> idToMail = new HashMap<>();
		List<Internship> internships = internshipDao.getInternships();
		internships.stream()
			.forEach(i -> idToMail.put(i.getId()+"", i.getMailContactPerson()));
		
		return idToMail;
	}

	@Override
	public Map<String, List<Preference>> getStudentsPreferences() {
		List<Student> students = studentDao.getStudents();
		
		Map<String, List<Preference>> studentPreferences = new HashMap<>();
		students.stream()
			.forEach(s -> studentPreferences.put(s.getNif(), preferenceDao.getPreference(s.getNif())));
		
		return studentPreferences;
	}

	@Override
	public Map<String, String> getProjectOffersToTitles() {
		List<ProjectOffer> projectOffers = projectOfferDao.getProjectOffers();
		Map<String, String> offersToTitles = new HashMap<>();
		projectOffers.stream()
			.forEach(po -> offersToTitles.put(po.getId()+"", po.getTitle()));
		return offersToTitles;
	}

	@Override
	public Map<String, Assignment> studentAssignProjects() {
		Map<String, Assignment> projectForStudent = new HashMap<>();
		
		List<Student> students = studentDao.getStudents();
		students.stream()
			.forEach(s -> projectForStudent.put(s.getNif(), assignmentDao.getAssignment(s.getNif())));
		
		return projectForStudent;
	}

	@Override
	public Map<String, Boolean> isProjectAssigned() {
		Map<String, Boolean> projectForStudent = new HashMap<>();
		
		List<Assignment> assignments = assignmentDao.getAssignments();
		assignments.stream()
			.forEach(a -> projectForStudent.put(a.getNif_student(), a.getState()));
	
		return projectForStudent;
	}

	@Override
	public Map<String, String> studentNifToProjectAssigned() {
		List<Assignment> assignments = assignmentDao.getAssignments();
		List<ProjectOffer> projectOffers = projectOfferDao.getProjectOffers();
		
		Map<Long, String> offersToTitles = new HashMap<>();
		projectOffers.stream()
			.forEach(po -> offersToTitles.put(po.getId(), po.getTitle()));
		
		Map<String, String> matches = new HashMap<>();
		
		assignments.stream()
			.forEach(a -> matches.put(a.getNif_student(), offersToTitles.get(a.getId_projectoffer())));

		return matches;
	}
<<<<<<< HEAD
=======
	
	
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
}
