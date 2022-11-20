package com.ssafy.news.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.BoardDto;
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
	public int writeArticle(BoardDto boardDto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
