package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UsersController {
//    @PostMapping("/register")
//    public String addUser(@RequestParam("username") String username,
//                          @RequestParam("email") String email,
//                          @RequestParam("password") String password,
//                          @RequestParam("gender") String gender,
//                          @RequestParam("role") String role,
//                          @RequestParam("address") String address) {
//        System.out.println(username + " " + email + " " + password + " " + gender + " " + role + " " + address);
//        return "home";
//    }
	@Autowired
	UsersService service;
	
	@Autowired
	SongService Songservice;
	
	@PostMapping("/register")
		public String addUser(@ModelAttribute Users user) {
//		System.out.println(user.getUsername()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getGender()+" "+user.getRole()+" "+user.getAddress());
		boolean userstatus=service.emailExists(user.getEmail());
		if(userstatus==false) {
		service.addUsers(user);
		}else {
			System.out.println("user already exists");
		}
		return "login";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam ("password") String password,
			HttpSession session,Model model) {
		
		if(service.validateUsers(email,password)==true) {
			String role=service.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				List<Song> songList=Songservice.fetchAllSongs();
				model.addAttribute("songs",songList);
				model.addAttribute("isPremium",userStatus);
				 
				return "customerHome";
			}
		}else {
			return "login";
		}
		
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	session.invalidate();
	return "login";
	}
}
