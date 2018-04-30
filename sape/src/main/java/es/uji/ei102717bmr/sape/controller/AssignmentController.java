package es.uji.ei102717bmr.sape.controller;

import java.util.Date;

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

@Controller
@RequestMapping("/assignment")
public class AssignmentController {
	
private AssignmentDAO assignmentDAO;
	
    @Autowired
    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
    	this.assignmentDAO = assignmentDAO;
    }
    
    @RequestMapping("/list") 
    public String listAssignments(Model model) {
        model.addAttribute("companies", assignmentDAO.getAssignments());
        return "@PathVariable String nifStudent, @PathVariable Date creationDate/list";
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
