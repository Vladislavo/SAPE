package es.uji.ei102717bmr.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei102717bmr.sape.dao.TutorDAO;
import es.uji.ei102717bmr.sape.model.Tutor;

@Controller
@RequestMapping("/tutor")
public class TutorController {

    private TutorDAO tutorDao; 

    @Autowired
    public void setTutorDao(TutorDAO tutorDao) {
        this.tutorDao = tutorDao;
    }
    @RequestMapping("/list") 
    public String listTutors(Model model) {
        model.addAttribute("tutors", tutorDao.getTutors());
        return "tutor/list";
    }
    @RequestMapping(value="/add") 
    public String addTutor(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutor/add";
    }
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("tutor") Tutor tutor,
                                    BindingResult bindingResult) {  
         if (bindingResult.hasErrors()) 
                return "tutor/add";
         tutorDao.addTutor(tutor);
         return "redirect:list.html"; 
     }
    @RequestMapping(value="/update/{mail}", method = RequestMethod.GET) 
    public String editTutor(Model model, @PathVariable String mail) { 
        model.addAttribute("tutor", tutorDao.getTutor(mail));
        return "tutor/update"; 
    }
    @RequestMapping(value="/update/{mail}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable String mail, 
                            @ModelAttribute("tutor") Tutor tutor, 
                            BindingResult bindingResult) {
         if (bindingResult.hasErrors()) 
             return "tutor/update";
         tutorDao.updateTutor(tutor);
         return "redirect:../list"; 
      }
    @RequestMapping(value="/delete/{mail}")
    public String processDelete(@PathVariable String mail) {
           tutorDao.deleteTutor(mail);;
           return "redirect:../list"; 
    }




}

