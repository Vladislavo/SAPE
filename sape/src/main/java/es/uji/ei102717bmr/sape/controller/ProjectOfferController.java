package es.uji.ei102717bmr.sape.controller;

import java.util.Date;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;

=======
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
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
<<<<<<< HEAD
import es.uji.ei102717bmr.sape.dao.StudentDAO;
import es.uji.ei102717bmr.sape.model.Preference;
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.Review;
import es.uji.ei102717bmr.sape.model.UserDetails;
import es.uji.ei102717bmr.sape.services.SapeServices;;
=======
import es.uji.ei102717bmr.sape.model.ProjectOffer;
import es.uji.ei102717bmr.sape.model.Review;
import es.uji.ei102717bmr.sape.services.SapeServices;
import es.uji.ei102717bmr.sape.services.SapeServicesImpl;;
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9

@Controller
@RequestMapping("/projectOffer")
public class ProjectOfferController {

<<<<<<< HEAD
	private ProjectOfferDAO projectOfferDao;
	private SapeServices sapeServices;
	private ReviewDAO reviewDao;
	private PreferenceDAO preferenceDAO;
	private StudentDAO studentDAO;
	private AssignmentDAO assignmentDAO;
	
=======
    private ProjectOfferDAO projectOfferDao;
    private SapeServices sapeServices;
    private ReviewDAO reviewDao;

>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
	@Autowired
	public void setSapeServicesImpl(SapeServices sapeServices){
		this.sapeServices = sapeServices;
	}
<<<<<<< HEAD
	
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

=======

    @Autowired
    public void setProjectOfferDao(ProjectOfferDAO projectOfferDao) {
        this.projectOfferDao=projectOfferDao;
    }
    
    @Autowired
    public void setReviewDao(ReviewDAO reviewDao) {
        this.reviewDao=reviewDao;
    }
    
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
    @RequestMapping("/list") 
    public String listProjectOffers(Model model) {
        model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
        model.addAttribute("projectCompanyMatches", sapeServices.projectIdCompanyNameMatches());
        model.addAttribute("internshipIdToMailContactPerson", sapeServices.internshipIdMailContactPerson());
        return "btc/offers/list";
    }
    @RequestMapping("/list/student")
	public String listProjectOffersStudent(HttpSession session, Model model) {
		UserDetails user = (UserDetails) session.getAttribute("user");
		String studentRole = "Student";
		System.out.println(user.getId().trim());
		if (user.getRole().trim().equals(studentRole)) {
			model.addAttribute("preference", new Preference());
			model.addAttribute("projectOffers", projectOfferDao.getProjectOffers());
			model.addAttribute("preferences", preferenceDAO.getPreference(user.getId().trim()));
			model.addAttribute("students", studentDAO.getStudent(user.getId().trim()));
			
			// if (session.getAttribute("user") == null)
			// {
			// model.addAttribute("user", new UserDetails());
			// return "redirect:../../index.html";
			// }

			return "student/list";
		} else {
			return "/home";
		}
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
<<<<<<< HEAD
    @RequestMapping(value = "/student/assignments/update/{state}&{nifStudent}", method = RequestMethod.POST)
	public String processUpdateAssignment(@PathVariable String nifStudent,
			@PathVariable boolean state) {
		assignmentDAO.updateAssignmentState(nifStudent, state);
		return "redirect:/projectOffer/list/student";
	}
=======
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

>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9

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
    @RequestMapping("/student/assignments/delete/{nifStudent}")
	public String processDelete(@PathVariable String nifStudent) {
		assignmentDAO.deleteAssignment(nifStudent);

		return "redirect:/projectOffer/list/student";
	}

}

