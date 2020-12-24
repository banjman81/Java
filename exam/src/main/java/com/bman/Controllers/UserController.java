package com.bman.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bman.Models.Course;
import com.bman.Models.Enroll;
import com.bman.Models.User;
import com.bman.Services.CourseService;
import com.bman.Services.EnrollService;
import com.bman.Services.UserService;
import com.bman.Validator.UserValidator;

@Controller
public class UserController {
	private final UserService userService;
	private final CourseService cService;
	private final UserValidator userValidator;
	private final EnrollService eService;
 
 public UserController(UserService userService, CourseService cService, UserValidator userValidator, EnrollService eService) {
     this.userService = userService;
     this.userValidator = userValidator;
     this.cService = cService;
     this.eService = eService;
 }
 
 @RequestMapping("/")
 public String indexregisterForm(@ModelAttribute("user") User user, HttpSession session) {
	 if(session.getAttribute("user_id") != null) {
		 return "redirect:/courses";
	 }
	 else {
		 return "index.jsp";
	 }
     
 }
 
 @RequestMapping("/courses")
 public String dashboard(@ModelAttribute("enroll") Enroll enroll, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
	 if(session.getAttribute("user_id") == null) {
		 //user is not logged in
		 redirectAttributes.addFlashAttribute("log_error", "Must be logged in to get access");
		 return "redirect:/";
	 }
	 else {
		 Long id = (Long) session.getAttribute("user_id");
		 User user = userService.findUserById(id);
		 model.addAttribute("user", user);
		 List<Course> courses = cService.allCourses();
		 model.addAttribute("courses", courses);
		 return "home.jsp";
	 }
 }
 // connecting user and course##############################################
 @PostMapping("/enroll")
 private String enroll(@Valid @ModelAttribute("enroll") Enroll enroll, BindingResult result) {
	 eService.addEnroll(enroll);
	 return "redirect:/courses";
 }
 
 @RequestMapping("/courses/new")
 public String indexregisterForm(@ModelAttribute("course") Course course, HttpSession session, Model model) {
	 Long id = (Long) session.getAttribute("user_id");
	 User user = userService.findUserById(id);
	 model.addAttribute("user", user);
     return "newCourse.jsp";
 }
 
 
 @PostMapping("/create/course")
 public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
	 if(result.hasErrors()) {
		 return "newCourse.jsp";
	 }
	 else {
		 cService.createCourse(course);
		 return "redirect:/courses";
	 }
 }
 
 @RequestMapping(value="/registration", method=RequestMethod.POST)
 public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
	 if(user.getPassword().equals(user.getPasswordConfirmation()) == false) {
		 //passwords do not match
		 redirectAttributes.addFlashAttribute("password_error", "password does not match");
		 return "redirect:/";
	 }
	 if(result.hasErrors()) {
		 return "index.jsp";
	 }
	 else {
		 User checkUser = userService.findByEmail(user.getEmail());
		 if(checkUser == null) {
		User saveUser = userService.registerUser(user);
		session.setAttribute("user_id", saveUser.getId());
		return "redirect:/courses";
		 }
		 else {
			 redirectAttributes.addFlashAttribute("email_error", "Email is  taken");
			 return "redirect:/";
		 }
	}
}
 
 // View, edit and  delete course ##################################
 
 @RequestMapping("/courses/{id}")
 public String showCourse(@PathVariable("id") Long id, Model model) {
	 Course c = cService.findCourse(id);
	 model.addAttribute("course", c);
     return "course.jsp";
 }
 
 @RequestMapping("/courses/{id}/edit")
 public String editCourse(@PathVariable("id") Long id, Model model) {
	 Course c = cService.findCourse(id);
	 model.addAttribute("course", c);
     return "editCourse.jsp";
 }
 
 
 @PostMapping("/edit/{id}")
 public String update(@Valid @ModelAttribute("course") Course course, BindingResult result, @PathVariable("id") Long id, Model model) {
	 if(result.hasErrors()) {
		 return "editCourse.jsp";
	 }
	 cService.editCourse(course);
	 return "redirect:/courses/"+id;
 }
 
 @RequestMapping(value="/courses/{id}/delete", method=RequestMethod.DELETE)
 	public String destroy(@PathVariable("id") Long id) {
	 	cService.deleteCourse(id);
	 	return "redirect:/courses";
 }
 
 //LOGIN #####################################################
 @RequestMapping(value="/login", method=RequestMethod.POST)
 public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
	 boolean isValid = userService.authenticateUser(email, password);
	 if(isValid == true) {
		 User validUser = userService.findByEmail(email);
		 session.setAttribute("user_id", validUser.getId());
		 return "redirect:/courses";
	 }
	 else {
		 redirectAttributes.addFlashAttribute("login_error", "Invalid login information");
		 return "redirect:/";
	 }
 }

 
 @RequestMapping("/logout")
 public String logout(HttpSession session) {
     session.invalidate();
     return "redirect:/";
 }
}

