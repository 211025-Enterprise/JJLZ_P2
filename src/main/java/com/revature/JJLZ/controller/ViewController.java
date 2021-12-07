package com.revature.JJLZ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("register")
	public String register() {
		return "register";
	} // route register.html

	@GetMapping("login")
	public String login() { return "login"; // route login.html
	}
	@GetMapping("detail")
	public String detail() {
		return "detail";
	} // route detail.html
}
