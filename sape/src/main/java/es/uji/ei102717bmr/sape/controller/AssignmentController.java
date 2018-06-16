package es.uji.ei102717bmr.sape.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102717bmr.sape.dao.AssignmentDAO;
import es.uji.ei102717bmr.sape.model.Assignment;
import es.uji.ei102717bmr.sape.model.Preference;
import es.uji.ei102717bmr.sape.model.Student;
import es.uji.ei102717bmr.sape.services.SapeServices;
import es.uji.ei102717bmr.sape.services.SapeServicesImpl;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {
	
private AssignmentDAO assignmentDAO;
private SapeServices sapeServices;

	@Autowired
	public void setSapeServicesImpl(SapeServices sapeServicesImpl){
		this.sapeServices = sapeServicesImpl;
	}
	
    @Autowired
    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
    	this.assignmentDAO = assignmentDAO;
    }
    
    @RequestMapping("/list") 
    public String listAssignments(Model model) {
    	model.addAttribute("assignment", new Assignment());
    	// list of all students
    	model.addAttribute("students", sapeServices.getStudents());
    	// map student nif -> list of preferences
    	model.addAttribute("studentsPreferences", sapeServices.getStudentsPreferences());
    	// map id_projectOffer -> project title
    	model.addAttribute("profectOfferToTitle", sapeServices.getProjectOffersToTitles());
    	
    	model.addAttribute("tutors", sapeServices.getTutors());
    	
    	model.addAttribute("assignments", assignmentDAO.getAssignments());
        model.addAttribute("studentsNotAssigned", sapeServices.studentsWithoutProjectAssigned());
        model.addAttribute("projectOffersNotAssigned", sapeServices.projectOffersNotAssigned());
        model.addAttribute("tutors", sapeServices.getTutors());
        
        Map<String, String> projectOffersMap = new HashMap<>();
        sapeServices.getProjectOffers().stream()
        	.forEach(projectOffer -> projectOffersMap.put(""+projectOffer.getId(),  projectOffer.getTitle()));
        model.addAttribute("projectOffersMap", projectOffersMap);
        
        Map<String, String> studentsMap = new HashMap<>();
        sapeServices.getStudents().stream()
        	.forEach(student -> studentsMap.put(student.getNif(),  student.getName()));
        
        model.addAttribute("studentsMap", studentsMap);
        
        Map<String, String> tutorsMap = new HashMap<>();
        sapeServices.getTutors().stream()
        	.forEach(tutor -> tutorsMap.put(tutor.getMail(),  tutor.getName()));
        
        model.addAttribute("tutorsMap", tutorsMap);
        
        model.addAttribute("studentNifAssignment", sapeServices.studentAssignProjects());
        
        model.addAttribute("isAssignned", sapeServices.isProjectAssigned());
        
        model.addAttribute("studentNifToProjectAssigned", sapeServices.studentNifToProjectAssigned());
        
        return "btc/assignments/list";
    }
    
    @RequestMapping(value="/add") 
    public String addAssignment(Model model) {
        model.addAttribute("assignment", new Assignment());
        return "assignment/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("assignment") Assignment assignment, 
    		@ModelAttribute("tutor_mail_form") String mail_tutor, 
    		@ModelAttribute("id_projectoffer") String id_projectoffer,
    		@ModelAttribute("nif_student") String nif_student,
    		BindingResult bindingResult) {  
    	
    	if (bindingResult.hasErrors()) return "btc/assignments/list";
    		assignment.setMail_tutor(mail_tutor);
    		assignment.setId_projectoffer(Long.valueOf(id_projectoffer));
    		assignment.setNif_student(nif_student);
    		assignmentDAO.addAssignment(assignment);
    	return "btc/assignments/list"; 
    }
    
    @RequestMapping(value="/update/{nif_student}&{id_projectoffer}", method = RequestMethod.GET) 
    public String editAssignment(Model model, @PathVariable String nif_student, @PathVariable String id_projectoffer) { 
        model.addAttribute("assignment", assignmentDAO.getAssignment(nif_student, Long.valueOf(id_projectoffer)));
        model.addAttribute("assignment", new Assignment());
    	
    	model.addAttribute("assignments", assignmentDAO.getAssignments());
        model.addAttribute("studentsNotAssigned", sapeServices.studentsWithoutProjectAssigned());
        model.addAttribute("projectOffersNotAssigned", sapeServices.projectOffersNotAssigned());
        model.addAttribute("tutors", sapeServices.getTutors());
        
        return "btc/assignments/edit"; 
    }
    
    @RequestMapping(value="/update/{nif_student}&{id_projectoffer}", method = RequestMethod.POST) 
    public String processEditSubmit(@PathVariable String nif_student, @PathVariable String id_projectoffer, @ModelAttribute("assignment") Assignment assignment, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) return "assignment/update";
    	assignmentDAO.updateAssignment(assignment);
    	return "redirect:../list"; 
      }
    
    @RequestMapping(value="/delete/{nif_student}&{id_projectoffer}")
    public String processDelete(@PathVariable String nif_student, 
    							@PathVariable String id_projectoffer) {

    	assignmentDAO.deleteAssignment(nif_student, Long.valueOf(id_projectoffer));
    	return "redirect:../list"; 
    }

}
