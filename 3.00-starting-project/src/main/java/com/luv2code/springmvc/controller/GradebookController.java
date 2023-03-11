package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.services.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

	@Autowired
	private StudentAndGradeService studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		Iterable<CollegeStudent>students=studentService.getGradeBook();
		m.addAttribute("students",students);
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createStudents(@ModelAttribute("student") CollegeStudent student,
								 Model m) {
		studentService.createStudent(student.getFirstname(),
				                     student.getLastname(),
				                     student.getEmailAddress());
		Iterable<CollegeStudent>students=studentService.getGradeBook();
		m.addAttribute("students",students);
		return "index";
	}

	@RequestMapping(value = "/delete/student/{id}", method = RequestMethod.GET)
		public String deleteStudent(@PathVariable int id,Model m){
		if(!studentService.checkIfStudentIsNull(id)){
		studentService.deleteStudent(id);
		Iterable<CollegeStudent>students=studentService.getGradeBook();
		m.addAttribute("students",students);
		return "index";
		}
		return "error";
	}

	@GetMapping("/studentInformation/{id}")
		public String studentInformation(@PathVariable int id, Model m) {
		return "studentInformation";
		}

}
