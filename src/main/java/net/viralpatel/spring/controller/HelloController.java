package net.viralpatel.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	Logger LOGGER = LogManager.getLogger(HelloController.class);

	@GetMapping("/hello")
	public String hello(Model model) {
		LOGGER.error("This is a log");
		model.addAttribute("name", "John Doe");

		return "welcome";
	}
}
