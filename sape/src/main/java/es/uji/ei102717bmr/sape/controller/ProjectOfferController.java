package es.uji.ei102717bmr.sape.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102717bmr.sape.dao.AssignmentDAO;
import es.uji.ei102717bmr.sape.dao.PreferenceDAO;
import es.uji.ei102717bmr.sape.dao.ProjectOfferDAO;
import es.uji.ei102717bmr.sape.dao.ReviewDAO;
import es.uji.ei102717bmr.sape.dao.StudentDAO;
import es.uji.ei102717bmr.sape.model.Preference;
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.Review;
import es.uji.ei102717bmr.sape.model.UserDetails;
import es.uji.ei102717bmr.sape.services.SapeServices;;

@Controller
@RequestMapping("/projectOffer")
public class ProjectOfferController {

	private ProjectOfferDAO projectOfferDao;
	private SapeServices sapeServices;
	private ReviewDAO reviewDao;
	private PreferenceDAO preferenceDAO;
	private StudentDAO studentDAO;
	private AssignmentDAO assignmentDAO;
	
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
	@Autowired
	public void setPreferenceDao(PreferenceDAO preferenceDAO) {
		this.preferenceDAO = preferenceDAO;
	}
	
	@Autowired
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@Autowired
	public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
		this.assignmentDAO = assignmentDAO;
	}

    @RequestMapping("/list") 
    public String listProjectOffers(HttpSession session, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	switch(user.getRole().trim()) {
    		case "BTC": {
    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
    	        
    	        return "btc/offers/list";
    		}
    		case "DCC": {
    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
    	        
    	        return "dcc/offers/list";
    		}
    		case "Student": {
    			model.addAttribute("preference", new Preference());
    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
    			model.addAttribute("preferences", preferenceDAO.getPreference(user.getId().trim()));
    			model.addAttribute("students", studentDAO.getStudent(user.getId().trim()));
    			model.addAttribute("assignment", assignmentDAO.getAssignment(user.getId().trim()));
    		
    		
    			return "student/list";
    		}
    	}
    	return "/home";
    }
    @RequestMapping("/student/assignments/list")
    public String listAssigmentsStudent(HttpSession session, Model model) {
            UserDetails user = (UserDetails) session.getAttribute("user");
            model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
            model.addAttribute("assignments", assignmentDAO.getAssignment(user.getId().trim()));
            System.out.println(assignmentDAO.getAssignment(user.getId().trim()));


            return "student/assignments/list";
    }


    @RequestMapping(value="/add") 
    public String addProjectOffer(HttpSession session, Model model) {
        model.addAttribute("projectOffer", new ProjectOffer());
        return "projectOffer/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(HttpSession session, @ModelAttribute("projectOffer") ProjectOffer projectOffer,
                                    BindingResult bindingResult) {   
    	if (bindingResult.hasErrors()) 
                return "projectOffer/add";
	    projectOfferDao.addProjectOffer(projectOffer);
	    return "redirect:list.html"; 
    }
    
    @RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
    public String editProjectOffer(HttpSession session, Model model, @PathVariable long id) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user.getRole().trim().equals("BTC")) {
	        model.addAttribute("projectOffer", projectOfferDao.getProjectOffer(id));
	        return "btc/offers/edit"; 
    	} else {
    		return "/home";
    	}
    }
    
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(HttpSession session, @PathVariable long id, 
                            @ModelAttribute("projectOffer") ProjectOffer projectOffer,
                            @ModelAttribute("review") String review_str,
                            BindingResult bindingResult) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user.getRole().trim().equals("BTC")) {
	    	if (bindingResult.hasErrors()) 
	             return "btc/offers/edit";
	         projectOfferDao.updateProjectOffer(projectOffer);
	         Review review = new Review();
	         review.setText(review_str);
	         review.setCreationDate(new Date());
	         review.setProjectOfferId(projectOffer.getId());
	         reviewDao.addReview(review);
	         
	         return "redirect:../list"; 
        } else {
        	return "/home";
        }
    }
    
    // should go into assignments but there is a conflicts of path arguments
    @RequestMapping(value = "/student/assignments/update/{state}&{nifStudent}", method = RequestMethod.POST)
	public String processUpdateAssignment(HttpSession session, @PathVariable String nifStudent,
			@PathVariable boolean state) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user.getRole().trim().equals("Student")) {
	    	assignmentDAO.updateAssignmentState(nifStudent, state);
			return "redirect:/projectOffer/list";
    	} else {
    		return "/home";
    	}
	}

    @RequestMapping(value="/delete/{id}")
    public String processDelete(HttpSession session, @ModelAttribute("reject") String reviewText, @PathVariable long id) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user.getRole().trim().equals("BTC")) {
	    	Review review = new Review();
	        review.setText(reviewText);
	        review.setCreationDate(new Date());
	        review.setProjectOfferId(id);
	        reviewDao.addReview(review);
	        
	    	projectOfferDao.deleteProjectOffer(id);
	    	return "redirect:../list";
    	} else {
    		return "/home";
    	}
    }
    // should go into assignments but there is a conflicts of path arguments
    @RequestMapping("/student/assignments/delete/{nifStudent}")
	public String processDelete(HttpSession session, @PathVariable String nifStudent) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user.getRole().trim().equals("Student")) {
			assignmentDAO.deleteAssignment(nifStudent);
	
			return "redirect:/projectOffer/list";
	    } else {
    		return "/home";
    	}
	}

}

