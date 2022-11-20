package com.ssafy.news.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.BoardDto;

public interface NewsService {

	int writeArticle(BoardDto boardDto) throws Exception;
	
}
