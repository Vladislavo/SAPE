package es.uji.ei102717bmr.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei102717bmr.sape.dao.InternshipDAO;
import es.uji.ei102717bmr.sape.model.Internship;

@Controller
@RequestMapping("/internship")
public class InternshipController {
	
	private InternshipDAO internshipDAO;
	
    @Autowired
    public void setInternshipDAO(InternshipDAO internshipDAO) {
    	this.internshipDAO = internshipDAO;
    }
    
    @RequestMapping("/list") 
    public String listInternship(Model model) {
        model.addAttribute("internships", internshipDAO.getInternships());
        return "internship/list";
    }
    
    @RequestMapping(value="/add") 
    public String addInternship(Model model) {
        model.addAttribute("internship", new Internship());
        return "internship/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("internship") Internship internship, BindingResult bindingResult) {  
    	if (bindingResult.hasErrors()) return "internship/add";
    	internshipDAO.addInternship(internship);
    	return "redirect:list.html"; 
    }
    
    @RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
    public String editInternship(Model model, @PathVariable long id) { 
        model.addAttribute("internship", internshipDAO.getInternship(id));
        return "internship/update"; 
    }
    
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable long id, @ModelAttribute("internship") Internship internship, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) return "internship/update";
    	internshipDAO.updateInternship(internship);
    	return "redirect:../list"; 
      }
    
    @RequestMapping(value="/delete/{id}")
    public String processDelete(@PathVariable long id) {
    	internshipDAO.deleteInternship(id);
    	return "redirect:../list"; 
    }

}
