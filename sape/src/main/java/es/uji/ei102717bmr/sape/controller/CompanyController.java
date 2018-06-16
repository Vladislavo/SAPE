package es.uji.ei102717bmr.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei102717bmr.sape.dao.CompanyDAO;
import es.uji.ei102717bmr.sape.model.Company;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	private CompanyDAO companyDAO;
	
    @Autowired
    public void setCompanyDAO(CompanyDAO companyDAO) {
    	this.companyDAO = companyDAO;
    }
    
    @RequestMapping("/list") 
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyDAO.getCompanies());
        return "company/list";
    }
    
    @RequestMapping(value="/add") 
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/add";
    }
    
    @RequestMapping(value="/internship/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("company") Company company, BindingResult bindingResult) {  
    	if (bindingResult.hasErrors()) return "company/add";
    	companyDAO.addCompany(company);;
    	return "redirect:/list.html"; 
    }
    
    @RequestMapping(value="/update/{cif}", method = RequestMethod.GET) 
    public String editcompany(Model model, @PathVariable String cif) { 
        model.addAttribute("company", companyDAO.getCompany(cif));
        return "company/update"; 
    }
    
    @RequestMapping(value="/update/{cif}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable String cif, @ModelAttribute("company") Company company, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) return "company/update";
    	companyDAO.updateCompany(company);
    	return "redirect:../list"; 
      }
    
    @RequestMapping(value="/delete/{cif}")
    public String processDelete(@PathVariable String cif) {
    	companyDAO.deleteCompany(cif);
    	return "redirect:../list"; 
    }

}
