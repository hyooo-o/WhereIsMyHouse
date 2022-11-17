package com.ssafy.qna.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.qna.AnswerDto;
import com.ssafy.qna.QuestionDto;
import com.ssafy.qna.model.service.QnaService;

@Controller
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {
	private final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	private final QnaService qnaService;

	@Autowired
	public QnaController(QnaService qnaService) {
		logger.info("QnaController 생성자 호출");
		this.qnaService = qnaService;
	}
	
//	@GetMapping("/list")
//	public String list() {
//		return "redirect:/Qna/Qnalist.html";
//	}

	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<QuestionDto>> list() throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<QuestionDto>>(qnaService.listArticle(), HttpStatus.OK);
	}
	
	@PostMapping("/write/question")
	@ResponseBody
	private ResponseEntity<?> write(@RequestBody QuestionDto qnaDto) throws Exception {
		try {
			logger.info("writeArticle - 호출");
			logger.info(qnaDto.toString());
			if (qnaService.writeArticle(qnaDto) != 0) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/view/{questionId}")
	@ResponseBody
	private ResponseEntity<?> view(@PathVariable int questionId) throws Exception {
		try {
			QuestionDto qnaDto = qnaService.getArticle(questionId);
			logger.info("getArticle - 호출");
			if (qnaDto != null) {
				return new ResponseEntity<QuestionDto>(qnaDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/modify/question/{questionId}")
	@ResponseBody
	private ResponseEntity<?> mvModify(@PathVariable int questionId) throws Exception {
		try {
			QuestionDto qnaDto = qnaService.getArticle(questionId);
			logger.info("getArticle - 호출");
			if (qnaDto != null) {
				return new ResponseEntity<QuestionDto>(qnaDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
//		return "redirect:/Qna/Qnamodify.html";
//	}

	@PutMapping("/modify/question")
	@ResponseBody
	private ResponseEntity<?> modify(@RequestBody QuestionDto qnaDto) throws Exception {
		try {
//			QnaDto.setArticleNo(articleNo);
			logger.info("modifyArticle - 호출");
			if (qnaService.modifyArticle(qnaDto)) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete/question/{questionId}")
	@ResponseBody
	private ResponseEntity<?> delete(@PathVariable int questionId) throws Exception {
		try {
			logger.info("deleteArticle - 호출");
			if (qnaService.deleteArticle(questionId)) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	@GetMapping("/search")
//	@ResponseBody
//	private ResponseEntity<?> search(@RequestBody Map<String, String> map) throws Exception {
//		try {
//			List<QuestionDto> list = qnaService.searchArticle(map);
//			
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<QuestionDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	/////////answer/////////
	
	@GetMapping("/answer/{questionId}")
	@ResponseBody
	private ResponseEntity<?> getAns(@PathVariable int questionId) throws Exception {
		try {
			AnswerDto answerDto = qnaService.getAnswer(questionId);
			logger.info("getArticle - 호출");
			if (answerDto != null) {
				return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/write/answer")
	@ResponseBody
	private ResponseEntity<?> writeAns(@RequestBody AnswerDto answerDto) throws Exception {
		try {
			logger.info("writeAns - 호출");
			logger.info(answerDto.toString());
			if (qnaService.writeAnswer(answerDto) != 0) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	@GetMapping("/modify/answer/{questionId}")
//	@ResponseBody
//	private ResponseEntity<?> mvModifyAns(@PathVariable int questionId) throws Exception {
//		try {
//			AnswerDto answerDto = qnaService.getAnswer(questionId);
//			logger.info("getArticle - 호출");
//			if (answerDto != null) {
//				return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

	@PutMapping("/modify/answer")
	@ResponseBody
	private ResponseEntity<?> modifyAns(@RequestBody AnswerDto answerDto) throws Exception {
		try {
//			QnaDto.setArticleNo(articleNo);
			logger.info("modifyAns - 호출");
			if (qnaService.modifyAnswer(answerDto)) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/delete/answer/{questionId}")
	@ResponseBody
	private ResponseEntity<?> deleteAns(@PathVariable int questionId) throws Exception {
		try {
			logger.info("deleteAns - 호출");
			if (qnaService.deleteAnswer(questionId)) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
