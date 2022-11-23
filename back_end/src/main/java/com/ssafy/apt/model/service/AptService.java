package com.ssafy.apt.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.apt.AptDto;
import com.ssafy.apt.AptSearchDto;
import com.ssafy.apt.DealChartDto;
import com.ssafy.apt.TradeDto;

public interface AptService {
	List<AptDto> search(String dong) throws Exception;	// 아파트 정보 읽어오기	

	void regist(AptDto aptDto)throws Exception;

	void deleteTrade(int id) throws Exception;

	void updateTrade(TradeDto tradeDto) throws Exception;
	
	List<DealChartDto> aptTradePrice(String aptCode) throws Exception;

	ArrayList<AptSearchDto> getList(Map<String, Double> loc) throws Exception;
}
