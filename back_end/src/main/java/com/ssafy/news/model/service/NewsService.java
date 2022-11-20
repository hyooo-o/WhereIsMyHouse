package com.ssafy.news.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.news.NewsDto;


public interface NewsService {
	ArrayList<NewsDto> getAllNews() throws SQLException;
}
