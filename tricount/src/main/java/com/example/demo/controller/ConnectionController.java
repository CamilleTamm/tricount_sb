package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class ConnectionController {
	
	@Autowired UserRepository userRepository;

	private boolean emailExists(String email) {
		for(User u : userRepository.findAll()) {
			if(u.getEmail().equals(email)) {
				return true;
			}
		}
		
		return false;
	}
	
	private String getUserMdp(String email) {
		for(User u : userRepository.findAll()) {
			if(u.getEmail().equals(email)) {
				return u.getMdp();
			}
		}
		
		return null;
	}
	
	public Long getUserId(String email) {
		for(User u : userRepository.findAll()) {
			if(u.getEmail().equals(email)) {
				return u.getId();
			}
		}
		
		return (long) 0;
	}
	
	@GetMapping("/")
	public String home(Model model) {
		return "/connection";
	}
	
	@PostMapping("/se-connecter")
	public String seConnecter(@Validated User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/";
		}
		
		if(!emailExists(user.getEmail())) {
			return "redirect:/";
		}
		
		if(!getUserMdp(user.getEmail()).equals(user.getMdp())) {
			return "redirect:/";
		}
		
		HomeController.CURRENT_ID = getUserId(user.getEmail());
		HomeController.CURRENT_EMAIL = user.getEmail();
		
		return "redirect:/home";
	}
	
	@PostMapping("/creer-compte")
	public String creerCompte(@Validated User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/";
		}
		
		if(user.getEmail().trim().equals("") || emailExists(user.getEmail())) {
			return "redirect:/";
		}

		userRepository.save(user);
		
		return "redirect:/";
	}
}
