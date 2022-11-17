package com.ssafy.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.info.AccomodationDto;
import com.ssafy.info.ClinicDto;
import com.ssafy.info.CommercialDto;
import com.ssafy.info.DongDto;
import com.ssafy.info.HospitalDto;
import com.ssafy.info.model.service.InfoService;
import com.ssafy.member.model.MemberDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/info")
@CrossOrigin("*")
public class InfoController {
	
	@Autowired
	InfoService infoService;
	
	// register Area 
	@PostMapping("/registArea")
	@ResponseBody
	public ResponseEntity<?> registArea(@RequestParam Map<String,String> map,HttpSession session){
		String gugun = map.get("gugun");
		String dong = map.get("dong");
		
		String id = ((MemberDto)(session.getAttribute("userinfo"))).getUserId();
		
		//System.out.println(gugun + " : " + dong + " : " + id);
		
		try {
			
			int cnt = infoService.registArea(id, gugun, dong);
			if(cnt>0) {
				return new ResponseEntity<String>(gugun,HttpStatus.OK);	
			}else {
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
	}
	
	
	// getinfo
	@GetMapping("/view")
	@ResponseBody
	public ResponseEntity<?> getinfo(HttpSession session){

		String userId = ((MemberDto)session.getAttribute("userinfo")).getUserId();
		//String userId = "ssafy";
		//System.out.println(userId);
		
		try {
			// 쿼리 => dongDto
			DongDto dongDto = infoService.favorite(userId);
			System.out.println(dongDto.toString());
			
			String gugun = dongDto.getSi_code() + "";
			String dong = dongDto.getBub_code() + "";
			
			ArrayList<ClinicDto> clist = infoService.getClinicInfo(gugun, dong);
			ArrayList<CommercialDto> cmlist = infoService.getCommercialInfo(gugun, dong);
			ArrayList<AccomodationDto> alist = infoService.getAccommodationDtoInfo(gugun, dong);
			ArrayList<HospitalDto> hlist = infoService.getHospitalInfo(gugun, dong);
			
			Map<String,List<?>> map = new HashMap<>();
			map.put("clinics", clist);
			map.put("commercialAreas", cmlist);
			map.put("accs", alist);
			map.put("hospitals", hlist);
			
			return new ResponseEntity<Object>(map,HttpStatus.OK);		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return exceptionHandling(e);
		}
		
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
