package com.ssafy.apt.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.apt.AptDto;
import com.ssafy.apt.AptSearchDto;
import com.ssafy.apt.TradeDto;

public interface AptService {
	List<AptSearchDto> search(Map<String, String> condition) throws Exception;	// 아파트 정보 읽어오기	

	void regist(AptDto aptDto)throws Exception;

	void deleteTrade(int id) throws Exception;

	void updateTrade(TradeDto tradeDto) throws Exception;
}
