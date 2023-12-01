package com.example.demo.App.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "index")
public class mainController {
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
}
	
	