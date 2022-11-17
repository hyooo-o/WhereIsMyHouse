package com.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.BoardDto;
import com.ssafy.board.model.mapper.BoardMapper;
//import com.ssafy.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public int writeArticle(BoardDto boardDto) throws Exception {
		return boardMapper.writeArticle(boardDto);
	}

	@Override
	public List<BoardDto> listArticle() throws Exception {
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int spl = SizeConstant.SIZE_PER_LIST;
//		int start = (pgno - 1) * spl;
//		map.put("start", start + "");
		return boardMapper.listArticle();
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);
	}

	@Override
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return boardMapper.modifyArticle(boardDto);
	}

	@Override
	public boolean deleteArticle(int articleNo) throws Exception {
		return boardMapper.deleteArticle(articleNo);
	}

	@Override
	public List<BoardDto> searchArticle(Map<String, String> map) throws Exception {
		return boardMapper.searchArticle(map);
	}

}
