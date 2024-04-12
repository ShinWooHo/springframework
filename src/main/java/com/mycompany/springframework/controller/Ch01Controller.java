package com.mycompany.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch01")
public class Ch01Controller {
//	private static final Logger LOGGER = 
	// LoggerFactory.getLogger(Ch01Controller.class);

	@RequestMapping("/content")
	public String content(String chNum, Model model) {
		// LOGGER.info("content(); 실행");
		log.info("content(); 실행");

		model.addAttribute("chNum", chNum);
		return "ch01/content";

	}

}
