package com.ssafy.member.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.loginMember(userId, userPwd);
	}

	@Override
	public int modifyMember(MemberDto memberDto) throws SQLException {
		return memberMapper.modifyMember(memberDto);
	}

	@Override
	public int delMember(String userid) throws SQLException {
		return memberMapper.delMember(userid);
	}

	@Override
	public String searchPwd(String userid) throws SQLException {
		return memberMapper.searchPwd(userid);
	}

	@Override
	public MemberDto search(String userid) throws SQLException {
		return memberMapper.search(userid);
	}

}
