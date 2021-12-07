package com.revature.JJLZ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	@GetMapping("/")
	public String welcome() {
		return "homepage.html";
	}

	@GetMapping("/home")
	public String home() {
		return "userhome.html";
	}
}
