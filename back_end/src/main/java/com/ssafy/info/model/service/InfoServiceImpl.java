package com.ssafy.info.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.info.AccomodationDto;
import com.ssafy.info.ClinicDto;
import com.ssafy.info.CommercialDto;
import com.ssafy.info.DongDto;
import com.ssafy.info.HospitalDto;
import com.ssafy.info.model.dao.InfoDao;
import com.ssafy.info.model.mapper.InfoMapper;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoMapper infoMapper;

	@Override
	public int registArea(String id, String gugun, String dong) throws SQLException {
		// TODO Auto-generated method stub
		int si_code = Integer.parseInt(dong.substring(0,5));
		int bub_code = Integer.parseInt(dong.substring(5));
		String user_id = id;
		
		Map<String,Object> map = new HashMap<>();
		map.put("si_code",si_code);
		map.put("bub_code",bub_code);
		map.put("user_id",user_id);
		
		return infoMapper.registArea(map);
	}
	
	@Override
	public ArrayList<ClinicDto> getClinicInfo(String gugun, String dong) throws Exception {
		return infoMapper.getClinicInfo(gugun, dong);
	}

	@Override
	public ArrayList<CommercialDto> getCommercialInfo(String gugun, String dong) throws Exception {
		// TODO Auto-generated method stub
		return infoMapper.getCommercialInfo(gugun, dong);
	}

	@Override
	public ArrayList<HospitalDto> getHospitalInfo(String gugun, String dong) throws Exception {
		// TODO Auto-generated method stub
		return infoMapper.getHospitalInfo(gugun, dong);
	}

	@Override
	public DongDto favorite(String userId) throws SQLException {
		return infoMapper.favorite(userId);
	}

	@Override
	public ArrayList<AccomodationDto> getAccommodationDtoInfo(String gugun, String dong) throws SQLException {
		// TODO Auto-generated method stub
		return infoMapper.getAccommodationDtoInfo(gugun, dong);
	}

	
}
