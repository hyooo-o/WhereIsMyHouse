package com.ssafy.info.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.info.AccomodationDto;
import com.ssafy.info.ClinicDto;
import com.ssafy.info.CommercialDto;
import com.ssafy.info.DongDto;
import com.ssafy.info.HospitalDto;

@Mapper
public interface InfoMapper {
	
	ArrayList<ClinicDto> getClinicInfo(String gugun, String dong) throws Exception;

	ArrayList<CommercialDto> getCommercialInfo(String gugun, String dong) throws Exception;

	ArrayList<HospitalDto> getHospitalInfo(String gugun, String dong) throws Exception;

	DongDto favorite(String userId) throws SQLException;
	
	ArrayList<AccomodationDto> getAccommodationDtoInfo(String gugun, String dong) throws SQLException;

	int registArea(Map<String,Object> map) throws SQLException;
}
