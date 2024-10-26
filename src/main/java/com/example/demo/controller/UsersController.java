package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongsService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService userv;
	@Autowired
	SongsService sserv;
	@PostMapping("/register")
		public String addUser(@ModelAttribute Users user) {
			
			boolean userstatus = userv.emailExists(user.getEmailid());
			if (userstatus == false) {
				userv.addUser(user);
				return "registersuccess";
			} else {
				return "registerfail";

			}
			
		}

		@PostMapping("/login")
		public String validateUser(@RequestParam String emailid, @RequestParam String password,HttpSession session) {

			if (userv.validateUser(emailid, password) == true) {
				session.setAttribute("emailid",emailid);
				if (userv.getRole(emailid).equals("admin")) {
					return "adminhome";
				} else {
					return "customer";

				}

			} else {
				return "loginfail";
			}
		}
		

		@GetMapping("/exploresongs")
		public String exploreSongs(HttpSession session, Model model) {
			String emailid=(String) session.getAttribute("emailid");
			Users user=userv.getUser(emailid);
			boolean userStatus=user.isPremium();
			if(userStatus==true) {
				List<Songs> songslist=sserv.fetchAllSongs();
				model.addAttribute("songslist", songslist);
			
				return "displaysongs";
			}else {
				return "payment";
			}
		}
}




