package com.ssafy.apt.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.apt.AptDto;
import com.ssafy.apt.AptSearchDto;
import com.ssafy.apt.DealChartDto;
import com.ssafy.apt.DealDto;
import com.ssafy.apt.TradeDto;

public interface AptMapper {
	List<AptDto> dongSearch(String dong) throws SQLException;
	List<AptDto> aptSearch(String apartmentName) throws SQLException;
	AptDto getApt(String aptCode) throws SQLException;
	void regist(AptDto aptDto)throws SQLException;
	void deleteTrade(int id)throws SQLException;
	void updateTrade(TradeDto tradeDto) throws SQLException;
	List<DealChartDto> aptTradePrice(String name) throws SQLException;
	ArrayList<AptSearchDto> getList(Map<String, Double> loc) throws SQLException;
	List<DealDto> aptDealList(String aptCode) throws SQLException;
}
