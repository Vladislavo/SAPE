package es.uji.ei102717bmr.sape.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei102717bmr.sape.dao.ReviewDAO;
import es.uji.ei102717bmr.sape.model.Review;
import es.uji.ei102717bmr.sape.model.UserDetails;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private ReviewDAO reviewDao; 

    @Autowired
    public void setReviewDao(ReviewDAO reviewDao) {
        this.reviewDao = reviewDao;
    }
    @RequestMapping("/list/{id_projectOffer}") 
    public String listReviews(HttpSession session, Model model, @PathVariable String id_projectOffer) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        if(user != null){
        	model.addAttribute("user", user);
        	model.addAttribute("reviews", reviewDao.getReviews(Long.valueOf(id_projectOffer)));
        } else {
        	model.addAttribute("user", new UserDetails());
        	return "signin";
        }
        return "btc/offers/listReviews";
    }
    @RequestMapping(value="/add") 
    public String addReview(Model model) {
        model.addAttribute("review", new Review());
        return "review/add";
    }
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("review") Review review,
                                    BindingResult bindingResult) {  
         if (bindingResult.hasErrors()) 
                return "review/add";
         reviewDao.addReview(review);
         return "redirect:list.html"; 
     }
    @RequestMapping(value="/update/{idProjectOffer}/{creationDate}", method = RequestMethod.GET) 
    public String editReview(Model model, @PathVariable long idProjectOffer, @PathVariable Date creationDate) { 
        model.addAttribute("review", reviewDao.getReview(idProjectOffer, creationDate));
        return "review/update"; 
    }
    @RequestMapping(value="/update/{idProjectOffer}/{creationDate}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable long idProjectOffer, @PathVariable Date creationDate, 
                            @ModelAttribute("review") Review review, 
                            BindingResult bindingResult) {
         if (bindingResult.hasErrors()) 
             return "review/update";
         reviewDao.updateReview(review);
         return "redirect:../list"; 
      }
    @RequestMapping(value="/delete/{idProjectOffer}/{creationDate}")
    public String processDelete(@PathVariable long idProjectOffer, @PathVariable Date creationDate) {
           reviewDao.deleteReview(idProjectOffer, creationDate);
           return "redirect:../list"; 
    }




}

