package es.uji.ei102717bmr.sape.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102717bmr.sape.dao.UserDAO;
import es.uji.ei102717bmr.sape.model.UserDetails;


class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
       UserDetails userDetails = (UserDetails) obj;
       if(userDetails.getMail().trim().equals("")){
    	   errors.rejectValue("email", "obligatory", "You must enter an email");
       }
       if(userDetails.getPassword().trim().equals("")){
    	   errors.rejectValue("password", "obligatory", "You must enter a password");
       }
    }
}

@Controller
public class SigninController {
	@Autowired
    private UserDAO userDao;

    @RequestMapping("/signin")
    public String signin(Model model, HttpSession session) {
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
					return "redirect:/projectOffer/list";
				}
    		}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
        return "signin";
    }

    @RequestMapping(value="/signin", method=RequestMethod.POST)
    public String checkSignin(@ModelAttribute("user") UserDetails user,
                BindingResult bindingResult, HttpSession session) {
        
    	UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signin";
        }
        // Check that the login is correct
        // by trying to load the user data
        user = userDao.loadUserByMail(user.getMail(),user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "User does not exist or incorrect password was introduced");
            return "signin";
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
				return "redirect:/projectOffer/list";
			}
        }
        
        return "signin";
    }

    @RequestMapping("/signout")
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

