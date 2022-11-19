package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception; // 아이디 중복검사
	int joinMember(MemberDto memberDto) throws Exception; // 회원가입
	MemberDto loginMember(MemberDto memberDto) throws Exception; // 로그인
	int modifyMember(MemberDto memberDto) throws SQLException;
	int delMember(String userId) throws SQLException;
	String searchPwd(String userId) throws SQLException;
	MemberDto search(String userid) throws SQLException;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
}
