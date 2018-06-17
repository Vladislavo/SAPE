package es.uji.ei102717bmr.sape.controller;

import javax.servlet.http.HttpSession;

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
import es.uji.ei102717bmr.sape.dao.UserProvider;
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.UserDetails;
import es.uji.ei102717bmr.sape.model.Internship;
import es.uji.ei102717bmr.sape.services.SapeServicesImpl;

@Controller
@RequestMapping("/internship")
public class InternshipController {
	
	private InternshipDAO internshipDAO;
	private SapeServicesImpl sapeServicesImpl;
	private ProjectOfferDAO projectOfferDAO;
	private String studentRole = "Company";
	
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
    public String listInternship(HttpSession session, Model model) {
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getRole().trim().equals(studentRole)) {
			model.addAttribute("user", user);
	        model.addAttribute("internships", internshipDAO.getInternships());
	        model.addAttribute("projectOffers", sapeServicesImpl.getProjectOffers());
	        return "internship/list";
		}
		return "/signin";
    }
    
    @RequestMapping(value="/add") 
    public String addInternship(HttpSession session, Model model) {
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getRole().trim().equals(studentRole)) {
			model.addAttribute("user", user);
	        model.addAttribute("internships", internshipDAO.getInternships());
	        Internship internship = new Internship();
	        model.addAttribute("internship", internship);
	        return "internship/add";
		}
		return "/signin";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(HttpSession session, @ModelAttribute("internship") Internship internship, @ModelAttribute("projectofferTitle") String title, BindingResult bindingResult) {  
    	if (bindingResult.hasErrors()) return "internship/add";
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getRole().trim().equals(studentRole)) {
			internship.setCif_Company(user.getId());
	    	internshipDAO.addInternship(internship);
	    	ProjectOffer projectOffer = new ProjectOffer();
	    	projectOffer.setTitle(title);
	    	projectOfferDAO.addProjectOffer(projectOffer);
	    	return "redirect:../internship/list"; 
		}
		return "/signin";
    }
    
    @RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
    public String editInternship(HttpSession session, Model model, @PathVariable long id) {
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getRole().trim().equals(studentRole)) {
			model.addAttribute("user", user);
	        model.addAttribute("internship", internshipDAO.getInternship(id));
	        return "internship/update"; 
		}
		return "/signin";
    }
    
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(HttpSession session, @PathVariable long id, @ModelAttribute("internship") Internship internship, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) return "internship/update";
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getRole().trim().equals(studentRole)) {
	    	internshipDAO.updateInternship(internship);
	    	return "redirect:../list";
		}
		return "/signin";
    }
    
    @RequestMapping(value="/projectOffer/update/{id}", method = RequestMethod.GET) 
    public String editProjectOffer(HttpSession session, Model model, @PathVariable long id) {
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getRole().trim().equals(studentRole)) {
			model.addAttribute("user", user);
	        model.addAttribute("projectOffer", projectOfferDAO.getProjectOfferInternship(id));
	        return "internship/projectOffer/update";
		}
		return "/signin";
    }
    
    @RequestMapping(value="/projectOffer/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(HttpSession session, @PathVariable long id, @ModelAttribute("projectOffer") ProjectOffer projectOffer, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) return "internship/projectOffer/update";
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getRole().trim().equals(studentRole)) {
	    	projectOfferDAO.updateProjectOfferInternship(projectOffer);
	    	return "redirect:../../list";
		}
		return "/signin";
    }
    
    @RequestMapping(value="/delete/{id}")
    public String processDelete(@PathVariable long id) {
    	internshipDAO.deleteInternship(id);
    	return "redirect:../list"; 
    }

}
