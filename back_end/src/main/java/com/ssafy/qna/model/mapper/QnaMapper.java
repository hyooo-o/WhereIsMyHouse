package com.ssafy.qna.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.qna.AnswerDto;
import com.ssafy.qna.QuestionDto;

@Mapper
public interface QnaMapper {

	int writeArticle(QuestionDto questionDto) throws SQLException;
	List<QuestionDto> listArticle() throws SQLException;
	int totalArticleCount(Map<String, String> map) throws SQLException;
	QuestionDto getArticle(int questionId) throws SQLException;
	List<QuestionDto> searchArticle(Map<String, String> map) throws SQLException;
	boolean modifyArticle(QuestionDto questionDto) throws SQLException;
	boolean deleteArticle(int questionId) throws SQLException;
	
	int writeAnswer(AnswerDto answerDto) throws SQLException;
	AnswerDto getAnswer(int questionId) throws SQLException;
	boolean modifyAnswer(AnswerDto answerDto) throws SQLException;
	boolean deleteAnswer(int questionId) throws SQLException;
	
}
