package es.uji.ei102717bmr.sape.controller;

import java.util.Date;
import java.util.HashMap;
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
import es.uji.ei102717bmr.sape.services.SapeServicesImpl;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {
	
private AssignmentDAO assignmentDAO;
private SapeServicesImpl sapeServicesImpl;

	@Autowired
	public void setSapeServicesImpl(SapeServicesImpl sapeServicesImpl){
		this.sapeServicesImpl = sapeServicesImpl;
	}
	
    @Autowired
    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
    	this.assignmentDAO = assignmentDAO;
    }
    
    @RequestMapping("/list") 
    public String listAssignments(Model model) {
    	model.addAttribute("assignment", new Assignment());
    	
    	model.addAttribute("assignments", assignmentDAO.getAssignments());
        model.addAttribute("studentsNotAssigned", sapeServicesImpl.studentsWithoutProjectAssigned());
        model.addAttribute("projectOffersNotAssigned", sapeServicesImpl.projectOffersNotAssigned());
        model.addAttribute("tutors", sapeServicesImpl.getTutors());
        
        Map<String, String> projectOffersMap = new HashMap<>();
        sapeServicesImpl.getProjectOffers().stream()
        	.forEach(projectOffer -> projectOffersMap.put(""+(projectOffer.getId()),  projectOffer.getTitle()));
        System.out.println(projectOffersMap);
        model.addAttribute("projectOffersMap", projectOffersMap);
        
        Map<String, String> studentsMap = new HashMap<>();
        sapeServicesImpl.getStudents().stream()
        	.forEach(student -> studentsMap.put(student.getNif(),  student.getName()));
        
        model.addAttribute("studentsMap", studentsMap);
        
        Map<String, String> tutorsMap = new HashMap<>();
        sapeServicesImpl.getTutors().stream()
        	.forEach(tutor -> tutorsMap.put(tutor.getMail(),  tutor.getName()));
        
        model.addAttribute("tutorsMap", tutorsMap);
        
        return "btc/assignments/list";
    }
    
    @RequestMapping(value="/add") 
    public String addAssignment(Model model) {
        model.addAttribute("assignment", new Assignment());
        return "assignment/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("assignment") Assignment assignment, BindingResult bindingResult) {  
    	if (bindingResult.hasErrors()) return "assignment/add";
    		assignmentDAO.addAssignment(assignment);
    	return "redirect:list.html"; 
    }
    
    @RequestMapping(value="/update/{nif_Student}&{creationDate}", method = RequestMethod.GET) 
    public String editAssignment(Model model, @PathVariable String nifStudent, @PathVariable Date creationDate) { 
        model.addAttribute("assignment", assignmentDAO.getAssignment(nifStudent, creationDate));
        return "assignment/update"; 
    }
    
    @RequestMapping(value="/update/{nif_Student}&{creationDate}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable String nifStudent, @PathVariable Date creationDate, @ModelAttribute("assignment") Assignment assignment, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) return "assignment/update";
    	assignmentDAO.updateAssignment(assignment);
    	return "redirect:../list"; 
      }
    
    @RequestMapping(value="/delete/{nif_Student}&{creationDate}")
    public String processDelete(@PathVariable String nifStudent, @PathVariable Date creationDate) {
    	assignmentDAO.deleteAssignment(nifStudent, creationDate);
    	return "redirect:../list"; 
    }

}
