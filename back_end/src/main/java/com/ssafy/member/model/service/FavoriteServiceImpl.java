package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.FavoriteDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.FavoriteMapper;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private FavoriteMapper favoriteMapper;

	@Override
	public FavoriteDto getFavorite(FavoriteDto favoriteDto) throws SQLException {
		return favoriteMapper.getFavorite(favoriteDto);
	}
	
	@Override
	public int addFavorite(FavoriteDto favoriteDto) throws SQLException {
		return favoriteMapper.addFavorite(favoriteDto);
	}

	@Override
	public int deleteFavorite(FavoriteDto favoriteDto) throws SQLException {
		return favoriteMapper.deleteFavorite(favoriteDto);
	}

}
