//package com.ssafy.board.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.board.BoardDto;
//import com.ssafy.board.model.service.BoardService;
//
//@RestController
//@RequestMapping("/board")
//@CrossOrigin("*")
//public class BoardRestController {
//	
//	private final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
//	
//	@Autowired
//	private BoardService boardService;
//	
//	@Autowired
//	public BoardRestController(BoardService boardService) {
//		logger.info("boardRestController 생성자 호출");
//		this.boardService = boardService;
//	}
//	
//	@GetMapping("/list")
//	private ResponseEntity<?> list() throws Exception {
//		try {
//			List<BoardDto> list = boardService.listArticle();
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
//	@GetMapping("/write")
//	private ResponseEntity<?> mvwrite(@RequestBody Map<String, String> map) {
//		return null;
//	}
//	
//	@PostMapping("/write")
//	private ResponseEntity<?> write(@RequestBody BoardDto boardDto) throws Exception {
//		try {
//			boardService.writeArticle(boardDto);
//	
//			List<BoardDto> list = boardService.listArticle();
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
//	@GetMapping("/view/{articleNo}")
//	private ResponseEntity<?> view(@PathVariable int articleNo) throws Exception {
//		try {
//			BoardDto boardDto = boardService.getArticle(articleNo);
//			boardService.updateHit(articleNo);
//			if (boardDto != null) {
//				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
//	@GetMapping("/modify/{articleNo}")
//	private ResponseEntity<?> mvModify(@PathVariable int articleNo) throws Exception {
//		BoardDto boardDto = boardService.getArticle(articleNo);
//		
//		if (boardDto != null) {
//			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
//
//	@PutMapping("/modify/{articleNo}")
//	private ResponseEntity<?> modify(@RequestBody BoardDto boardDto, @PathVariable int articleNo) throws Exception {
//		try {
////			boardDto.setArticleNo(articleNo);
//			boardService.modifyArticle(boardDto);
//			
//			List<BoardDto> list = boardService.listArticle();
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
//	@DeleteMapping("/delete/{articleNo}")
//	private ResponseEntity<?> delete(@PathVariable int articleNo) throws Exception {
//		try {
//			boardService.deleteArticle(articleNo);
//			
//			List<BoardDto> list = boardService.listArticle();
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
//	private ResponseEntity<String> exceptionHandling(Exception e) {
//        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//	
//}
