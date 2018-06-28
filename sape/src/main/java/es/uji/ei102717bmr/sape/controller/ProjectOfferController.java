package es.uji.ei102717bmr.sape.controller;

import java.util.Date;

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

import es.uji.ei102717bmr.sape.dao.AssignmentDAO;
import es.uji.ei102717bmr.sape.dao.InternshipDAO;
import es.uji.ei102717bmr.sape.dao.PreferenceDAO;
import es.uji.ei102717bmr.sape.dao.ProjectOfferDAO;
import es.uji.ei102717bmr.sape.dao.ReviewDAO;
import es.uji.ei102717bmr.sape.dao.StudentDAO;
import es.uji.ei102717bmr.sape.model.Assignment;
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
	private InternshipDAO internshipDAO;
	
	@Autowired
	public void setSapeServices(SapeServices sapeServices){
		this.sapeServices = sapeServices;
	}
	
	@Autowired
	public void setInternshipDao(InternshipDAO internshipDAO){
		this.internshipDAO = internshipDAO;
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
    	
    	if(user != null) {
    		model.addAttribute("user", user);
	    	switch(user.getRole().trim()) {
	    		case "BTC": {
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "btc/offers/list";
	    		}
	    		case "DCC": {
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "dcc/offers/list";
	    		}
	    		case "Student": {
	    			model.addAttribute("preference", new Preference());
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    			model.addAttribute("preferences", preferenceDAO.getPreference(user.getId().trim()));
	    			model.addAttribute("students", studentDAO.getStudent(user.getId().trim()));
	    		
	    			return "student/list";
	    		}
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    @RequestMapping("/list/revision") 
    public String listProjectOffersRevision(HttpSession session, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	
    	if(user != null) {
    		model.addAttribute("user", user);
    		//System.out.println(user);
	    	switch(user.getRole().trim()) {
	    		case "BTC": {
	    			model.addAttribute("projectOffers", sapeServices.getProjectOffersByState(0));
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "btc/offers/list";
	    		}
	    		case "DCC": {
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "dcc/offers/list";
	    		}
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    @RequestMapping("/list/accepted") 
    public String listProjectOffersAccepted(HttpSession session, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	
    	if(user != null) {
    		model.addAttribute("user", user);
	    	switch(user.getRole().trim()) {
	    		case "BTC": {
	    			model.addAttribute("projectOffers", sapeServices.getProjectOffersByState(1));
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "btc/offers/list";
	    		}
	    		case "DCC": {
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "dcc/offers/list";
	    		}
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    @RequestMapping("/list/visible") 
    public String listProjectOffersVisible(HttpSession session, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	
    	if(user != null) {
    		model.addAttribute("user", user);
	    	switch(user.getRole().trim()) {
	    		case "BTC": {
	    			model.addAttribute("projectOffers", sapeServices.getProjectOffersByState(2));
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "btc/offers/list";
	    		}
	    		case "DCC": {
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "dcc/offers/list";
	    		}
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    @RequestMapping("/list/proposed") 
    public String listProjectOffersProposed(HttpSession session, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	
    	if(user != null) {
    		model.addAttribute("user", user);
	    	switch(user.getRole().trim()) {
	    		case "BTC": {
	    			model.addAttribute("projectOffers", sapeServices.getProjectOffersByState(3));
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "btc/offers/list";
	    		}
	    		case "DCC": {
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "dcc/offers/list";
	    		}
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    @RequestMapping("/list/assigned") 
    public String listProjectOffersAssigned(HttpSession session, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	
    	if(user != null) {
    		model.addAttribute("user", user);
	    	switch(user.getRole().trim()) {
	    		case "BTC": {
	    			model.addAttribute("projectOffers", sapeServices.getProjectOffersByState(4));
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "btc/offers/list";
	    		}
	    		case "DCC": {
	    			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
	    	        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
	    	        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
	    	        model.addAttribute("projectIdToCompanyCif", sapeServices.projectIdCompanyCif());
	    	        
	    	        return "dcc/offers/list";
	    		}
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    
    @RequestMapping("/student/assignments/list")
    public String listAssigmentsStudent(HttpSession session, Model model) {
            UserDetails user = (UserDetails) session.getAttribute("user");
            model.addAttribute("user", user);
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
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
		        model.addAttribute("projectOffer", projectOfferDao.getProjectOffer(id));
		        return "btc/offers/edit"; 
	    	} else if(user.getRole().trim().equals("DCC")) {
	    		model.addAttribute("projectOffer", projectOfferDao.getProjectOffer(id));
		        return "dcc/offers/edit"; 
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    
    @RequestMapping(value="/update/{id}", method = RequestMethod.POST) 
    public String processUpdateSubmit(HttpSession session, @PathVariable long id, 
                            @ModelAttribute("projectOffer") ProjectOffer projectOffer,
                            @ModelAttribute("review") String review_str,
                            BindingResult bindingResult, Model model) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
		    	if (bindingResult.hasErrors()) 
		             return "btc/offers/edit";
		    	 ProjectOffer po = projectOfferDao.getProjectOffer(projectOffer.getId());
		    	 po.setItinerary(projectOffer.getItinerary());
		    	 po.setTitle(projectOffer.getTitle());
		    	 po.setTasks(projectOffer.getTasks());
		    	 po.setObjectives(projectOffer.getObjectives());
		    	 po.setLastChangeDate(new Date());
		         projectOfferDao.updateProjectOffer(po);
		         Review review = new Review();
		         review.setText(review_str);
		         review.setCreationDate(new Date());
		         review.setProjectOfferId(projectOffer.getId());
		         reviewDao.addReview(review);
		         
		         return "redirect:../list"; 
	        } else if(user.getRole().trim().equals("DCC")) {
	        	if (bindingResult.hasErrors()) 
		             return "dcc/offers/edit";
		    	 ProjectOffer po = projectOfferDao.getProjectOffer(projectOffer.getId());
		    	 po.setItinerary(projectOffer.getItinerary());
		    	 po.setLastChangeDate(new Date());
		         projectOfferDao.updateProjectOffer(po);
		         Review review = new Review();
		         review.setText(review_str);
		         review.setCreationDate(new Date());
		         review.setProjectOfferId(projectOffer.getId());
		         reviewDao.addReview(review);
		         
		         return "redirect:../list"; 
	        }
    	} else {
    		model.addAttribute("user", new UserDetails());
        }
    	return "signin";
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
    		return "signin";
    	}
	}

    @RequestMapping(value="/delete/{id}")
    public String processDelete(Model model, HttpSession session, @ModelAttribute("reject") String reviewText, @PathVariable long id) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
		    	Review review = new Review();
		        review.setText(reviewText);
		        review.setCreationDate(new Date());
		        review.setProjectOfferId(id);
		        reviewDao.addReview(review);
		        
		        ProjectOffer projectOffer = projectOfferDao.getProjectOffer(id);
		        internshipDAO.deleteInternship(projectOffer.getId_internship());
		        
		    	projectOfferDao.deleteProjectOffer(id);
		    	return "redirect:../list";
	    	} else {
	    		return "signin";
	    	}
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
    }
    // should go into assignments but there is a conflicts of path arguments
    @RequestMapping("/student/assignments/delete/{nifStudent}")
	public String processDelete(HttpSession session, @PathVariable String nifStudent) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user.getRole().trim().equals("Student")) {
			assignmentDAO.deleteAssignment(nifStudent);
	
			return "redirect:/projectOffer/list";
	    } else {
    		return "signin";
    	}
	}
    
    @RequestMapping("/publish/{id}")
	public String publishOffer(Model model, HttpSession session, @PathVariable String id) {
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC") || user.getRole().trim().equals("DCC")) {
	    		ProjectOffer po = projectOfferDao.getProjectOffer(Long.valueOf(id));
	    		po.setState((long) 2);
	    		projectOfferDao.updateProjectOffer(po);
				return "redirect:/projectOffer/list";
		    }
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
	}
    
    @RequestMapping("/approve/{id}")
	public String approveOffer(Model model, HttpSession session, @PathVariable String id) {
    	System.out.println("debug");
    	UserDetails user = (UserDetails) session.getAttribute("user");
    	if(user != null) {
    		model.addAttribute("user", user);
	    	if(user.getRole().trim().equals("BTC")) {
	    		ProjectOffer po = projectOfferDao.getProjectOffer(Long.valueOf(id));
	    		po.setState((long) 1);
	    		projectOfferDao.updateProjectOffer(po);
				return "redirect:/projectOffer/list";
		    }
    	} else {
    		model.addAttribute("user", new UserDetails());
    	}
    	return "signin";
	}
}

