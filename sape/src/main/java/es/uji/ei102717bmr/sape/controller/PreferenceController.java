package es.uji.ei102717bmr.sape.controller;



import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102717bmr.sape.dao.PreferenceDAO;
import es.uji.ei102717bmr.sape.model.Preference;
import es.uji.ei102717bmr.sape.dao.PreferenceDAO;
import es.uji.ei102717bmr.sape.dao.AssignmentDAO;
import es.uji.ei102717bmr.sape.model.Preference;
import es.uji.ei102717bmr.sape.model.UserDetails;
import es.uji.ei102717bmr.sape.dao.ProjectOfferDAO;


@Controller
@RequestMapping("/preference")
public class PreferenceController {
	
	private PreferenceDAO preferenceDAO;
	private ProjectOfferDAO projectOfferDAO;
	private AssignmentDAO assignmentDAO;
	
	@Autowired
    public void setPreferenceDao(PreferenceDAO preferenceDAO) {
        this.preferenceDAO = preferenceDAO;
    }
	@Autowired
    public void setProjectOfferDao(ProjectOfferDAO projectOfferDAO) {
        this.projectOfferDAO = projectOfferDAO;
    }
	@Autowired
    public void setAssignmentDao(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }
	@RequestMapping("/list") 
    public String listPreferences(HttpSession session, Model model) {
		UserDetails user = (UserDetails) session.getAttribute("user");
		String studentRole = "Student";
		if (user.getRole().trim().equals(studentRole)) {
	        model.addAttribute("preferences", preferenceDAO.getPreference(user.getId().trim()));
	        model.addAttribute("projectOffers", projectOfferDAO.getProjectOffers());
	        model.addAttribute("assignment", assignmentDAO.getAssignment(user.getId().trim()));
	        model.addAttribute("user", user);
	        
	        return "preference/list";
		}
		else {
			return "/home";
		}
    }
	
    @RequestMapping(value="/add") 
    public String addPreference(Model model) {
        model.addAttribute("student", new Preference());
        return "preference/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(HttpSession session, @ModelAttribute("preference") Preference preference,
                                    BindingResult bindingResult) {
         if (bindingResult.hasErrors()) 
                return "preference/add";
         UserDetails user = (UserDetails) session.getAttribute("user");
         preference.setStudent_nif(user.getId().trim());
         preference.setLastChangeDate(new Date());
         preferenceDAO.deletePreference(preference.getStudent_nif(), preference.getPreferenceOrder());
         preferenceDAO.deletePreferenceProject(user.getId().trim(), preference.getProjectOffer_id());
         preferenceDAO.addPreference(preference);
         return "redirect:../preference/list";
    }
    
    @RequestMapping(value="/update/{nifStudent}&{idProjectOffer}", method = RequestMethod.GET) 
    public String editStudent(Model model, @PathVariable String nif_Student,
    									   @PathVariable long id_ProjectOffer) { 
        model.addAttribute("preference", preferenceDAO.getPreference(nif_Student));
        return "preference/update"; 
    }
    
    @RequestMapping(value="/update/{nifStudent}&{idProjectOffer}", method = RequestMethod.POST)
    public String processUpdateSubmit(@PathVariable String nifStudent,
			   						  @PathVariable long idProjectOffer, 
			   						  @ModelAttribute("preference") Preference preference, 
			   						  BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) 
        	 return "preference/update";
    	preferenceDAO.updatePreference(preference);
    	return "redirect:../list"; 
    }
    
    @RequestMapping(value="/delete/{id_ProjectOffer}")
    public String processDelete(HttpSession session,
				  				@PathVariable long id_ProjectOffer) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	preferenceDAO.deletePreferenceProject(user.getId().trim(),id_ProjectOffer);
    	return "redirect:../list"; 
    }
	
}
