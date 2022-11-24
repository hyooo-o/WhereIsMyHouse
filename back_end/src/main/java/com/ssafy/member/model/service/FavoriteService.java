package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.member.model.FavoriteCardDto;
import com.ssafy.member.model.FavoriteDto;

public interface FavoriteService {
	FavoriteDto getFavorite(FavoriteDto favoriteDto) throws SQLException;
	int addFavorite(FavoriteDto favoriteDto) throws SQLException;
	int deleteFavorite(FavoriteDto favoriteDto) throws SQLException;
	ArrayList<FavoriteCardDto> getFavoriteInfo(String userId) throws SQLException;
}
