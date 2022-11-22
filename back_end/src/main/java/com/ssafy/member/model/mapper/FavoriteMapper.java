package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.FavoriteDto;
import com.ssafy.member.model.MemberDto;

@Mapper
public interface FavoriteMapper {
	FavoriteDto getFavorite(FavoriteDto favoriteDto) throws SQLException;
	int addFavorite(FavoriteDto favoriteDto) throws SQLException;
	int deleteFavorite(FavoriteDto favoriteDto) throws SQLException;
}
