package com.ssafy.news.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.news.NewsDto;

@Mapper
public interface NewsMapper {

	ArrayList<NewsDto> getAllNews() throws SQLException;

}
