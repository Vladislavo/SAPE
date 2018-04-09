package es.uji.ei102717bmr.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102717bmr.sape.dao.StudentDAO;
import es.uji.ei102717bmr.sape.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
	private StudentDAO studentDAO;
	
	@Autowired
    public void setStudentDao(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
	
	@RequestMapping("/list") 
    public String listStudents(Model model) {
        model.addAttribute("students", studentDAO.getStudents());
        return "student/list";
    }
	
    @RequestMapping(value="/add") 
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/add";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST) 
    public String processAddSubmit(@ModelAttribute("student") Student student,
                                    BindingResult bindingResult) {  
         if (bindingResult.hasErrors()) 
                return "student/add";
         studentDAO.addStudent(student);
         return "redirect:list.html"; 
    }
    
    @RequestMapping(value="/update/{NIF}", method = RequestMethod.GET) 
    public String editStudent(Model model, @PathVariable String NIF) { 
        model.addAttribute("student", studentDAO.getStudent(NIF));
        return "student/update"; 
    }
    
    @RequestMapping(value="/update/{NIF}", method = RequestMethod.POST) 
    public String processUpdateSubmit(@PathVariable String NIF, 
                            		  @ModelAttribute("student") Student student, 
                            		  BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) 
        	 return "student/update";
    	studentDAO.updateStudent(student);
    	return "redirect:../list"; 
    }
    
    @RequestMapping(value="/delete/{NIF}")
    public String processDelete(@PathVariable String NIF) {
    	studentDAO.deleteStudent(NIF);
    	return "redirect:../list"; 
    }
	
}
