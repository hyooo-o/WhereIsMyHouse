package com.ssafy.qna.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.qna.AnswerDto;
//import com.ssafy.util.SizeConstant;
import com.ssafy.qna.QuestionDto;
import com.ssafy.qna.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	private final QnaMapper qnaMapper;
	
	@Autowired
	public QnaServiceImpl(QnaMapper qnaMapper) {
		this.qnaMapper = qnaMapper;
	}

	@Override
	public int writeArticle(QuestionDto questionDto) throws Exception {
		return qnaMapper.writeArticle(questionDto);
	}

	@Override
	public List<QuestionDto> listArticle() throws Exception {
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int spl = SizeConstant.SIZE_PER_LIST;
//		int start = (pgno - 1) * spl;
//		map.put("start", start + "");
		return qnaMapper.listArticle();
	}

	@Override
	public QuestionDto getArticle(int questionId) throws Exception {
		return qnaMapper.getArticle(questionId);
	}

	@Override
	public boolean modifyArticle(QuestionDto questionDto) throws Exception {
		return qnaMapper.modifyArticle(questionDto);
	}

	@Override
	public boolean deleteArticle(int questionId) throws Exception {
		return qnaMapper.deleteArticle(questionId);
	}

	@Override
	public List<QuestionDto> searchArticle(Map<String, String> map) throws Exception {
		return qnaMapper.searchArticle(map);
	}

	@Override
	public int writeAnswer(AnswerDto answerDto) throws Exception {
		return qnaMapper.writeAnswer(answerDto);
	}

	@Override
	public boolean modifyAnswer(AnswerDto answerDto) throws Exception {
		return qnaMapper.modifyAnswer(answerDto);
	}

	@Override
	public boolean deleteAnswer(int questionId) throws Exception {
		return qnaMapper.deleteAnswer(questionId);
	}

	@Override
	public AnswerDto getAnswer(int questionId) throws Exception {
		return qnaMapper.getAnswer(questionId);
	}

}
