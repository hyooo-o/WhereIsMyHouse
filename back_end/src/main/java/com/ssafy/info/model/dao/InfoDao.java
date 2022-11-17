package com.ssafy.info.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.info.AccomodationDto;
import com.ssafy.info.ClinicDto;
import com.ssafy.info.CommercialDto;
import com.ssafy.info.DongDto;
import com.ssafy.info.HospitalDto;

public interface InfoDao {
	void registArea(String id, String gugun, String dong)throws SQLException;
	
	ArrayList<ClinicDto> getClinicInfo(String gugun, String dong) throws Exception;

	ArrayList<CommercialDto> getCommercialInfo(String gugun, String dong) throws Exception;

	ArrayList<HospitalDto> getHospitalInfo(String gugun, String dong) throws Exception;

	DongDto favorite(String userId) throws SQLException;
	
	ArrayList<AccomodationDto> getAccommodationDtoInfo(String gugun, String dong) throws SQLException;
}
