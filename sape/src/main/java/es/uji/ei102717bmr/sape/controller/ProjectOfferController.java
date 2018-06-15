package es.uji.ei102717bmr.sape.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei102717bmr.sape.dao.ProjectOfferDAO;
import es.uji.ei102717bmr.sape.dao.ReviewDAO;
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.Review;
import es.uji.ei102717bmr.sape.services.SapeServices;
import es.uji.ei102717bmr.sape.services.SapeServicesImpl;;

@Controller
@RequestMapping("/projectOffer")
public class ProjectOfferController {

    private ProjectOfferDAO projectOfferDao;
    private SapeServices sapeServices;
    private ReviewDAO reviewDao;

	@Autowired
	public void setSapeServicesImpl(SapeServices sapeServices){
		this.sapeServices = sapeServices;
	}

    @Autowired
    public void setProjectOfferDao(ProjectOfferDAO projectOfferDao) {
        this.projectOfferDao=projectOfferDao;
    }
    
    @Autowired
    public void setReviewDao(ReviewDAO reviewDao) {
        this.reviewDao=reviewDao;
    }
    
    @RequestMapping("/list") 
    public String listProjectOffers(Model model) {
        model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
        return "btc/offers/list";
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
        return "btc/offers/edit"; 
    }
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable long id, 
                            @ModelAttribute("projectOffer") ProjectOffer projectOffer,
                            @ModelAttribute("review") String review_str,
                            BindingResult bindingResult) {
         if (bindingResult.hasErrors()) 
             return "btc/offers/edit";
         projectOfferDao.updateProjectOffer(projectOffer);
         Review review = new Review();
         review.setText(review_str);
         review.setCreationDate(new Date());
         review.setProjectOfferId(projectOffer.getId());
         reviewDao.addReview(review);
         
         return "redirect:../list"; 
      }
    @RequestMapping(value="/delete/{id}")
    public String processDelete(@ModelAttribute("reject") String reviewText, @PathVariable long id) {
    	Review review = new Review();
        review.setText(reviewText);
        review.setCreationDate(new Date());
        review.setProjectOfferId(id);
        reviewDao.addReview(review);
        
    	projectOfferDao.deleteProjectOffer(id);
    	return "redirect:../list"; 
    }




}

