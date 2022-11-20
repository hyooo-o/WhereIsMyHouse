package com.ssafy.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.apt.AptSearchDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.news.NewsDto;
import com.ssafy.news.model.service.NewsService;

@Controller
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {
	private final Logger logger = LoggerFactory.getLogger(NewsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NewsService newsService;

	@GetMapping("/list")
	private ResponseEntity<Map<String, Object>> getAllNews() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			ArrayList<NewsDto> list = (ArrayList<NewsDto>) newsService.getAllNews();
			
			if (list != null && !list.isEmpty()) {
				resultMap.put("newsList", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
