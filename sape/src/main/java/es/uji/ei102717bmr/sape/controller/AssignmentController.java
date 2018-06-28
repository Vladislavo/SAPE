package es.uji.ei102717bmr.sape.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102717bmr.sape.dao.AssignmentDAO;
import es.uji.ei102717bmr.sape.model.Assignment;
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.UserDetails;
import es.uji.ei102717bmr.sape.services.SapeServices;

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
    public String listAssignments(HttpSession session, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	switch(user.getRole().trim()) {
	    		case "BTC": {
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

	    	        model.addAttribute("studentsMap", sapeServices.studentNifToName());
	    	        model.addAttribute("tutorsMap", sapeServices.tutorMailToName());
	    	        model.addAttribute("studentNifAssignment", sapeServices.studentAssignProjects());
	    	        model.addAttribute("isAssignned", sapeServices.isProjectAssigned());
	    	        model.addAttribute("studentNifToProjectAssigned", sapeServices.studentNifToProjectAssigned());
	    	        
	    	        return "btc/assignments/list";
	    		}
	    		case "Student": {
	    			model.addAttribute("projectOffers", sapeServices.getProjectOffers());
	    			model.addAttribute("assignments", assignmentDAO.getAssignment(user.getId().trim()));
	    			
	    			return "student/assignments/list";
	    		}
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "home";
    }
    
    @RequestMapping(value="/add/{nif_student}") 
    public String addAssignment(HttpSession session, Model model, @PathVariable String nif_student) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
	    		model.addAttribute("projectOffersNotAssigned", sapeServices.projectOffersNotAssigned());
		        model.addAttribute("tutors", sapeServices.getTutors());
	    		model.addAttribute("studentsMap", sapeServices.studentNifToName());
		        model.addAttribute("assignment", new Assignment());
		        model.addAttribute("nif_student", nif_student);
		        
		        return "btc/assignments/add";
	    	} 
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(HttpSession session, Model model, @ModelAttribute("assignment") Assignment assignment, 
    		@ModelAttribute("tutor_mail_form") String mail_tutor, 
    		@ModelAttribute("id_projectoffer") String id_projectoffer,
    		@ModelAttribute("nif_student") String nif_student,
    		BindingResult bindingResult) {
    	
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
	    		AssignmentValidator pov = new AssignmentValidator();
	        	pov.validate(assignment, bindingResult);
		    	if (bindingResult.hasErrors()) return "btc/assignments/add";
		    		if(assignment.getMail_tutor() == null) {
		    			assignment.setMail_tutor(mail_tutor);
			    		assignment.setId_projectoffer(Long.valueOf(id_projectoffer));
			    		assignment.setNif_student(nif_student);
		    		}
		    		System.out.println(assignment);
		    		assignmentDAO.addAssignment(assignment);
		    	return "redirect:/assignment/list";
	    	} 
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    
    @RequestMapping(value="/update/{nif_student}&{id_projectoffer}", method = RequestMethod.GET) 
    public String editAssignment(HttpSession session, Model model, @PathVariable String nif_student, @PathVariable String id_projectoffer) { 
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
	    		model.addAttribute("studentsMap", sapeServices.studentNifToName());
    	        model.addAttribute("tutorsMap", sapeServices.tutorMailToName());
	    		
		    	model.addAttribute("assignmentOld", assignmentDAO.getAssignment(nif_student, Long.valueOf(id_projectoffer)));
		    	session.setAttribute("assignmentOld", assignmentDAO.getAssignment(nif_student, Long.valueOf(id_projectoffer)));
		        model.addAttribute("assignment", new Assignment());
		        model.addAttribute("profectOfferToTitle", sapeServices.getProjectOffersToTitles());
		    	
		    	model.addAttribute("assignments", assignmentDAO.getAssignments());
		        model.addAttribute("studentsNotAssigned", sapeServices.studentsWithoutProjectAssigned());
		        model.addAttribute("projectOffersNotAssigned", sapeServices.projectOffersNotAssigned());
		        model.addAttribute("tutors", sapeServices.getTutors());
		        
		        return "btc/assignments/edit"; 
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    
    @RequestMapping(value="/update/{student_nif}&{projectoffer_id}", method = RequestMethod.POST) 
    public String processEditSubmit(Model model, HttpSession session, @PathVariable String student_nif,
    								@PathVariable String projectoffer_id, 
    								@ModelAttribute("assignment") Assignment assignment, 
    								BindingResult bindingResult) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
		    	if (bindingResult.hasErrors()) return "assignment/update/{nif_student}&{projectoffer_id}";
		    	Assignment as = (Assignment) session.getAttribute("assignmentOld");
		    	session.setAttribute("assignmentOld", null);
		    	as.setId_projectoffer(assignment.getId_projectoffer());
		    	as.setMail_tutor(assignment.getMail_tutor());
		    	as.setNif_student(student_nif);
		    	assignmentDAO.updateAssignment(as, projectoffer_id);
		    	return "redirect:../list";
	    	}
	    } else {
	    	model.addAttribute("user", new UserDetails());
	    }
    	return "signin";
    }
    
    @RequestMapping(value="/delete/{nif_student}&{id_projectoffer}")
    public String processDelete(Model model, HttpSession session, @PathVariable String nif_student, 
    							@PathVariable String id_projectoffer) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
		    	assignmentDAO.deleteAssignment(nif_student, Long.valueOf(id_projectoffer));
		    	return "redirect:../list"; 
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    
    class AssignmentValidator implements Validator {
        @Override
        public boolean supports(Class<?> cls) {
            return Assignment.class.isAssignableFrom(cls);
        }
        @Override
        public void validate(Object obj, Errors errors) {
        	Assignment assignment = (Assignment) obj;
           if(assignment.getId_projectoffer() < 0){
        	   errors.rejectValue("id_projectoffer", "bad value", "You must select a project offer.");
           }
        }
    }
}
