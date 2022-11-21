package com.ssafy.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.ssafy.board.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.member.model.MemberDto;

@Controller
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		logger.info("boardController 생성자 호출");
		this.boardService = boardService;
	}
	
	@GetMapping("/list/count")
	@ResponseBody
	public ResponseEntity<Integer> selectBoardTotalCount() throws Exception {
		logger.debug("totalArticleCount - 호출");
		return new ResponseEntity<Integer>(boardService.selectBoardTotalCount(), HttpStatus.OK);
	}

	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<?> list(int limit, int offset) throws Exception {
		try {
			logger.info("listArticle - 호출");
			System.out.println("limit : " + limit + " / offset : " + offset);
			return new ResponseEntity<List<BoardDto>>(boardService.listArticle(limit, offset), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/write")
	@ResponseBody
	private ResponseEntity<?> write(@RequestBody BoardDto boardDto) throws Exception {
		try {
			logger.info("writeArticle - 호출");
			logger.info(boardDto.toString());
			if (boardService.writeArticle(boardDto) != 0) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/view/{articleNo}")
	@ResponseBody
	private ResponseEntity<?> view(@PathVariable int articleNo) throws Exception {
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			logger.info("getArticle - 호출");
			boardService.updateHit(articleNo);
			if (boardDto != null) {
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/modify/{articleNo}")
	@ResponseBody
	private ResponseEntity<?> mvModify(@PathVariable int articleNo) throws Exception {
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			logger.info("getArticle - 호출");
			if (boardDto != null) {
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
//		return "redirect:/board/boardmodify.html";
//	}

	@PutMapping("/modify")
	@ResponseBody
	private ResponseEntity<?> modify(@RequestBody BoardDto boardDto) throws Exception {
		try {
//			boardDto.setArticleNo(articleNo);
			logger.info("modifyArticle - 호출");
			if (boardService.modifyArticle(boardDto)) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete/{articleNo}")
	@ResponseBody
	private ResponseEntity<?> delete(@PathVariable int articleNo) throws Exception {
		try {
			logger.info("deleteArticle - 호출");
			if (boardService.deleteArticle(articleNo)) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/search")
	@ResponseBody
	private ResponseEntity<?> search(@RequestBody Map<String, String> map) throws Exception {
		try {
			List<BoardDto> list = boardService.searchArticle(map);
			
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
