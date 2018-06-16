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
import es.uji.ei102717bmr.sape.dao.ProjectOfferDAO;
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.Internship;
import es.uji.ei102717bmr.sape.services.SapeServicesImpl;

@Controller
@RequestMapping("/internship")
public class InternshipController {
	
	private InternshipDAO internshipDAO;
	private SapeServicesImpl sapeServicesImpl;
	private ProjectOfferDAO projectOfferDAO;
	
	@Autowired
	public void setSapeServicesImpl(SapeServicesImpl sapeServicesImpl){
		this.sapeServicesImpl = sapeServicesImpl;
	}
	
    @Autowired
    public void setInternshipDAO(InternshipDAO internshipDAO) {
    	this.internshipDAO = internshipDAO;
    }
    
    @Autowired
    public void setProjectOfferDAO(ProjectOfferDAO projectOfferDAO) {
    	this.projectOfferDAO = projectOfferDAO;
    }
    
    @RequestMapping("/list") 
    public String listInternship(Model model) {
        model.addAttribute("internships", internshipDAO.getInternships());
        model.addAttribute("projectOffers", sapeServicesImpl.getProjectOffers());
        return "internship/list";
    }
    
    @RequestMapping(value="/add") 
    public String addInternship(Model model) {
        model.addAttribute("internships", internshipDAO.getInternships());
        Internship internship = new Internship();
        model.addAttribute("internship", internship);
        return "internship/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("internship") Internship internship, @ModelAttribute("projectofferTitle") String title, BindingResult bindingResult) {  
    	if (bindingResult.hasErrors()) return "internship/add";
    	internshipDAO.addInternship(internship);
    	ProjectOffer projectOffer = new ProjectOffer();
    	projectOffer.setTitle(title);
    	projectOfferDAO.addProjectOffer(projectOffer);
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
    	return "internship/list"; 
    }
    
    @RequestMapping(value="/projectOffer/update/{id}", method = RequestMethod.GET) 
    public String editProjectOffer(Model model, @PathVariable long id) { 
        model.addAttribute("projectOffer", projectOfferDAO.getProjectOfferInternship(id));
        return "internship/projectOffer/update"; 
    }
    
    @RequestMapping(value="/projectOffer/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable long id, @ModelAttribute("projectOffer") ProjectOffer projectOffer, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) return "internship/projectOffer/update";
    	projectOfferDAO.updateProjectOfferInternship(projectOffer);
    	return "internship/list"; 
    }
    
    @RequestMapping(value="/delete/{id}")
    public String processDelete(@PathVariable long id) {
    	internshipDAO.deleteInternship(id);
    	return "redirect:../list"; 
    }

}
