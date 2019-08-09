package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showHome() {
		return "/home/main";
	}
	@RequestMapping("home/main")
	public String showHome2() {
		return "/home/main";
	}
	@RequestMapping("member/main")
	public String showHome3() {
		return "/home/main";
	}
}
