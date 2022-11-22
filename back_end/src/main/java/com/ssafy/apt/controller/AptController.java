package com.ssafy.apt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apt.AptSearchDto;
import com.ssafy.apt.DealChartDto;
import com.ssafy.apt.DealDto;
import com.ssafy.apt.TradeDto;
import com.ssafy.apt.model.service.AptService;

@RestController
@RequestMapping("/apt")
public class AptController {

	private final Logger logger = LoggerFactory.getLogger(AptController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private AptService aptService;

	@Autowired
	public AptController(AptService aptService) {
		logger.info("aptController 생성자 호출");
		this.aptService = aptService;
	}

	@GetMapping("/list")
	private ResponseEntity<Map<String, Object>> getList(@RequestParam Map<String, Double> loc) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			ArrayList<AptSearchDto> list = (ArrayList<AptSearchDto>) aptService.getList(loc);
			
			if (list != null && !list.isEmpty()) {
				resultMap.put("aptList", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/search")
	private ResponseEntity<Map<String, Object>> search(@RequestParam Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			ArrayList<AptSearchDto> list = (ArrayList<AptSearchDto>) aptService.search(map);
			
			if (list != null && !list.isEmpty()) {
				resultMap.put("aptList", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	@PostMapping("/regist")
//	private ResponseEntity<?> regist(@RequestBody AptDto aptDto) {
//		try {
//			aptService.regist(aptDto);
//
//			return new ResponseEntity<Void>(HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
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

	@GetMapping("/chart/{aptCode}")
	private ResponseEntity<Map<String, Object>> aptTradePrice(@PathVariable("aptCode") String aptCode) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			List<DealChartDto> list = aptService.aptTradePrice(aptCode);
			logger.info("aptTradePrice 호출");
			System.out.println(aptCode);
			if (list != null && !list.isEmpty()) {
				resultMap.put("chartData", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
