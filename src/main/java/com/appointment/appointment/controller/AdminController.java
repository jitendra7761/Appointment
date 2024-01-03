package com.appointment.appointment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@PostMapping("/login")
	public String login() {
		// Your login logic here
		// For simplicity, we're returning a string indicating a successful login
		return "Login Successful!";
	}
}
