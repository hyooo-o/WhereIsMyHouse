package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.FavoriteDto;

public interface FavoriteService {
	FavoriteDto getFavorite(FavoriteDto favoriteDto) throws SQLException;
	int addFavorite(FavoriteDto favoriteDto) throws SQLException;
	int deleteFavorite(FavoriteDto favoriteDto) throws SQLException;
}
