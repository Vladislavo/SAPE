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
    public String indexSignin(Model model) {
        model.addAttribute("user", new UserDetails());
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
        user = userDao.loadUserByUsername(user.getEmail(),user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "User does not exist or incorrect password was introduced");
            return "home";
        }
        // Authenticated correctly.
        // Save the data of the authenticated user data in the session
        session.setAttribute("user", user);
        String nextUrl = (String) session.getAttribute("nextUrl");
        String returnUrl = "redirect:/";
        System.out.println(nextUrl + " fin");
        if (nextUrl != null){
        	returnUrl += nextUrl;
        } else {
        	
        }
        session.removeAttribute("nextUrl");
        // Return to the corresponding page
        return returnUrl;
    }
}

