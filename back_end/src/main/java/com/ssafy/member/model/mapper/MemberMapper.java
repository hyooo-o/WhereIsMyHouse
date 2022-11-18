package com.ssafy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	int idCheck(String userId) throws Exception; // 아이디 중복검사
	int joinMember(MemberDto memberDto) throws Exception; // 회원가입
	MemberDto loginMember(String userId, String userPwd) throws Exception; // 로그인
	int modifyMember(MemberDto memberDto) throws SQLException;
	int delMember(String userId) throws SQLException;
	String searchPwd(String userId) throws SQLException;
	MemberDto search(String userid) throws SQLException;
}
