package es.uji.ei102717bmr.sape.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102717bmr.sape.dao.UserDAO;
import es.uji.ei102717bmr.sape.model.UserDetails;

@Controller
public class IndexSigninController {
	@Autowired
    private UserDAO userDao;

    @RequestMapping("/home")
    public String indexSignin(Model model, HttpSession session) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	
    	if(user != null) {
    		switch(user.getRole().trim()) {
				case "BTC": {
					return "redirect:/projectOffer/list/";
				}
				case "DCC": {
					return "redirect:/projectOffer/list/";
				}
				case "Company": {
					return "redirect:/internship/list/";
				}
				case "Student": {
					return "redirect:/student/list";
				}
    		}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}

        return "home";
    }

    @RequestMapping(value="/home", method=RequestMethod.POST)
    public String checkIndexSignin(@ModelAttribute("user") UserDetails user,
                BindingResult bindingResult, HttpSession session) {
        
    	UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "home";
        }
        // Check that the login is correct
        // by trying to load the user data
        user = userDao.loadUserByMail(user.getMail(),user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "User does not exist or incorrect password was introduced");
            return "home";
        }
        // Authenticated correctly.
        // Save the data of the authenticated user data in the session
        session.setAttribute("user", user);
        
        switch(user.getRole().trim()) {
		case "BTC": {
			return "redirect:/projectOffer/list/";
		}
		case "DCC": {
			return "redirect:/projectOffer/list/";
		}
		case "Company": {
			return "redirect:/internship/list/";
		}
		case "Student": {
			return "redirect:/student/list";
		}
    }
    
    return "redirect:/signin";

    }
}

