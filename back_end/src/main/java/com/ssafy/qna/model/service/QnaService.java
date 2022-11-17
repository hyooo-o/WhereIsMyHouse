package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.qna.AnswerDto;
import com.ssafy.qna.QuestionDto;

public interface QnaService {

	int writeArticle(QuestionDto questionDto) throws Exception;
	List<QuestionDto> listArticle() throws Exception;
	QuestionDto getArticle(int questionId) throws Exception;
	List<QuestionDto> searchArticle(Map<String, String> map) throws Exception;
	boolean modifyArticle(QuestionDto questionDto) throws Exception;
	boolean deleteArticle(int questionId) throws Exception;
	
	int writeAnswer(AnswerDto answerDto) throws Exception;
	AnswerDto getAnswer(int questionId) throws Exception;
	boolean modifyAnswer(AnswerDto answerDto) throws Exception;
	boolean deleteAnswer(int questionId) throws Exception;
	
}
