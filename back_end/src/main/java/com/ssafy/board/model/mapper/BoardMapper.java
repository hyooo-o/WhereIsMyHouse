package com.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.BoardDto;

@Mapper
public interface BoardMapper {

	int writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> listArticle(int limit, int offset) throws SQLException;
	int selectBoardTotalCount() throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	List<BoardDto> searchArticle(Map<String, String> map) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	boolean modifyArticle(BoardDto boardDto) throws SQLException;
	boolean deleteArticle(int articleNo) throws SQLException;
	
}
