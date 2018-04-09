package es.uji.ei102717bmr.sape.controller;

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

@Controller
@RequestMapping("/preference")
public class PreferenceController {
	
	private PreferenceDAO preferenceDAO;
	
	@Autowired
    public void setPreferenceDao(PreferenceDAO preferenceDAO) {
        this.preferenceDAO = preferenceDAO;
    }
	
	@RequestMapping("/list") 
    public String listPreferences(Model model) {
        model.addAttribute("preferences", preferenceDAO.getPreferences());
        return "preference/list";
    }
	
    @RequestMapping(value="/add") 
    public String addPreference(Model model) {
        model.addAttribute("student", new Preference());
        return "preference/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("preference") Preference preference,
                                    BindingResult bindingResult) {  
         if (bindingResult.hasErrors()) 
                return "preference/add";
         preferenceDAO.addPreference(preference);
         return "redirect:list.html"; 
    }
    
    @RequestMapping(value="/update/{nif_Student}&{id_ProjectOffer}", method = RequestMethod.GET) 
    public String editStudent(Model model, @PathVariable String nif_Student,
    									   @PathVariable long id_ProjectOffer) { 
        model.addAttribute("preference", preferenceDAO.getPreference(nif_Student, id_ProjectOffer));
        return "preference/update"; 
    }
    
    @RequestMapping(value="/update/{nif_Student}&{id_ProjectOffer}", method = RequestMethod.POST)
    public String processUpdateSubmit(@PathVariable String nif_Student,
			   						  @PathVariable long id_ProjectOffer, 
			   						  @ModelAttribute("preference") Preference preference, 
			   						  BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) 
        	 return "preference/update";
    	preferenceDAO.updatePreference(preference);
    	return "redirect:../list"; 
    }
    
    @RequestMapping(value="/delete/{nif_Student}&{id_ProjectOffer}")
    public String processDelete(@PathVariable String nif_Student,
				  				@PathVariable long id_ProjectOffer) {
    	preferenceDAO.deletePreference(nif_Student, id_ProjectOffer);
    	return "redirect:../list"; 
    }
	
}
