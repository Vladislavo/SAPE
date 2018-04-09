package es.uji.ei102717bmr.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei102717bmr.sape.dao.ProjectOfferDAO;
import es.uji.ei102717bmr.sape.model.ProjectOffer;;

@Controller
@RequestMapping("/projectOffer")
public class ProjectOfferController {

    private ProjectOfferDAO projectOfferDao; 

    @Autowired
    public void setProjectOfferDao(ProjectOfferDAO projectOfferDao) {
        this.projectOfferDao=projectOfferDao;
    }
    @RequestMapping("/list") 
    public String listProjectOffers(Model model) {
        model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
        return "projectOffer/list";
    }
    @RequestMapping(value="/add") 
    public String addProjectOffer(Model model) {
        model.addAttribute("projectOffer", new ProjectOffer());
        return "projectOffer/add";
    }
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("projectOffer") ProjectOffer projectOffer,
                                    BindingResult bindingResult) {  
         if (bindingResult.hasErrors()) 
                return "projectOffer/add";
         projectOfferDao.addProjectOffer(projectOffer);
         return "redirect:list.html"; 
     }
    @RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
    public String editProjectOffer(Model model, @PathVariable long id) { 
        model.addAttribute("projectOffer", projectOfferDao.getProjectOffer(id));
        return "projectOffer/update"; 
    }
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable long id, 
                            @ModelAttribute("projectOffer") ProjectOffer projectOffer, 
                            BindingResult bindingResult) {
         if (bindingResult.hasErrors()) 
             return "projectOffer/update";
         projectOfferDao.updateProjectOffer(projectOffer);
         return "redirect:../list"; 
      }
    @RequestMapping(value="/delete/{nom}")
    public String processDelete(@PathVariable long id) {
           projectOfferDao.deleteProjectOffer(id);
           return "redirect:../list"; 
    }




}

