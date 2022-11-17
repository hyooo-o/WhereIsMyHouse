package com.ssafy.apt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apt.AptDto;
import com.ssafy.apt.AptSearchDto;
import com.ssafy.apt.TradeDto;
import com.ssafy.apt.model.service.AptService;
import com.ssafy.board.controller.BoardController;

@RestController
@RequestMapping("/apt")
public class AptController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private AptService aptService;

	@Autowired
	public AptController(AptService aptService) {
		logger.info("aptController 생성자 호출");
		this.aptService = aptService;
	}

	@PostMapping("/list")
	private ResponseEntity<?> search(@RequestParam Map<String, String> map) {
		try {
			ArrayList<AptSearchDto> list = (ArrayList<AptSearchDto>) aptService.search(map);
			
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AptSearchDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/regist")
	private ResponseEntity<?> regist(@RequestBody AptDto aptDto) {
		try {
			aptService.regist(aptDto);

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/delete/{trade_id}")
	private ResponseEntity<?> deleteTrade(@PathVariable("trade_id") int id){
		try {
			aptService.deleteTrade(id);

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/update")
	private ResponseEntity<?> updateTrade(@RequestBody TradeDto tradeDto) {
		try {
			aptService.updateTrade(tradeDto);

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
