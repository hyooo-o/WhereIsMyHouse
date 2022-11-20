package com.ssafy.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.news.model.service.NewsService;

@Controller
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {
	private final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
//	private final NewsService newsService;


	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
