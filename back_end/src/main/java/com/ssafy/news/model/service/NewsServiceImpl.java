package com.ssafy.news.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.BoardDto;
import com.ssafy.news.NewsDto;
import com.ssafy.news.model.mapper.NewsMapper;
//import com.ssafy.util.SizeConstant;

@Service
public class NewsServiceImpl implements NewsService {
	
	private NewsMapper newsMapper;
	
	@Autowired
	public NewsServiceImpl(NewsMapper newsMapper) {
		this.newsMapper = newsMapper;
	}

	@Override
	public ArrayList<NewsDto> getAllNews() throws SQLException {
		return newsMapper.getAllNews();
	}
}
