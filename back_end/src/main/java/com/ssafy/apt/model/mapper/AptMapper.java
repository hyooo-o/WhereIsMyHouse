package com.ssafy.apt.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.apt.AptDto;
import com.ssafy.apt.AptSearchDto;
import com.ssafy.apt.TradeDto;

public interface AptMapper {
	List<AptSearchDto> search(Map<String, String> condition) throws SQLException;
	void regist(AptDto aptDto)throws SQLException;
	void deleteTrade(int id)throws SQLException;
	void updateTrade(TradeDto tradeDto) throws SQLException;
	List<TradeDto> aptTradePrice(String name) throws SQLException;
	ArrayList<AptSearchDto> getList() throws SQLException;
}
