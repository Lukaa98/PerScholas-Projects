package net.java.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.java.springboot.model.User;
import net.java.springboot.repository.UserRepository;

@Controller
public class UserController 
{
	@Autowired // spring will autamticaly  create instance of this class user repositor in this case.
	// and inject its instance in this class and we can call methods from that class
	private UserRepository userRepository;
	
	@GetMapping("/login")
	
	public String viewHomePage(Model model)
	{
		//model.addAttribute("login" );

		return "login";
	}

	@GetMapping("/register")
	public String showSignUpForm(Model model)// we can send new user object to model
	{
		model.addAttribute("user", new User());

		return "signup_form";
	}	
	
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    userRepository.save(user);
	     
	    return "register_success";
	}
	
	

		//@GetMapping("/admin")
		//public String listUsers(Model model) {
			//List<User> listUsers = userRepository.findAll();
			//model.addAttribute("listUsers", listUsers);
		//	return "adminHome";
		//	}

}
