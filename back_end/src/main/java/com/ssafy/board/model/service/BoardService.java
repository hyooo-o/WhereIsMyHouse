package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.BoardDto;

public interface BoardService {

	int writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(int limit, int offset) throws Exception;
	int selectBoardTotalCount() throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	List<BoardDto> searchArticle(Map<String, String> map) throws Exception;
	void updateHit(int articleNo) throws Exception;
	boolean modifyArticle(BoardDto boardDto) throws Exception;
	boolean deleteArticle(int articleNo) throws Exception;
	
}
