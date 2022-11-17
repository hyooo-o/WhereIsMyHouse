//package com.ssafy.member.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.ssafy.member.model.MemberDto;
//import com.ssafy.member.model.service.MemberService;
//import com.ssafy.member.model.service.MemberServiceImpl;
//import com.ssafy.util.DBUtil;
//
//@WebServlet("/user")
//public class MemberController1 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private MemberService memberService;
//	
//	//private DBUtil dbUtil = DBUtil.getInstance();
//	
//	public void init() {
//		memberService = MemberServiceImpl.getMemberService();
//	}
//       
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String act = request.getParameter("act");
//		System.out.println("act ==== " + act);
//		
//		String path = "/index.jsp";
//		if("mvjoin".equals(act)) {
//			path = "/user/join.jsp";
//			redirect(request, response, path);
//		} else if("idcheck".equals(act)) {
//			int cnt = idCheck(request, response);
//			response.setContentType("text/plain;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println(cnt);
//		} else if("join".equals(act)) {
//			path = join(request, response);
//			forward(request, response, path);
//			//join(request, response);
//		} else if("login".equals(act)) {
//			path = login(request, response);
//			forward(request, response, path);
//		} else if("logout".equals(act)) {
//			path = logout(request, response);
//			redirect(request, response, path);
//		} else if("modify".equals(act)) {
//			path = modify(request, response);
//			forward(request, response, path);
//		} else if("del".equals(act)) {
//			path = del(request, response);
//			forward(request, response, path);
//		} else if("search".equals(act)) {
//			path = search(request, response);
//			forward(request, response, path);
//		} else {
//			redirect(request, response, path);
//		}
//	}
//
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//	}
//	
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
//		
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}
//	
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		try {
//			int count = memberService.idCheck(userId);
//			return count;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 500;
//	}
//	
//	private String modify(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		String userName = request.getParameter("username");
//		String userPwd = request.getParameter("userpwd");
//		String emailId = request.getParameter("emailid");
//		String emailDomain = request.getParameter("emaildomain");
//		
//		MemberDto memberdto = new MemberDto();
//		memberdto.setUserId(userId);
//		memberdto.setUserName(userName);
//		memberdto.setUserPwd(userPwd);
//		memberdto.setEmailDomain(emailDomain);
//		memberdto.setEmailId(emailId);
//		
//		try {
//			int cnt = memberService.modifyMember(memberdto);
//			//요청처리 정상 처리 : 메인화면으로 가기
//			if(cnt > 0) {
//				logout(request, response);
//				return "/index.jsp";
//			}
//			
//		} catch (Exception e) {
//			//처리 실패하는 경우 에러페이지로 이동
//			e.printStackTrace();
//			request.setAttribute("msg", "회원수정 중 에러 발생!");
//			return "/error/error.jsp";
//		}
//		
//		return "/index.jsp";
//	}
//	
//	private String join(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		String userName = request.getParameter("username");
//		String userPwd = request.getParameter("userpwd");
//		String emailId = request.getParameter("emailid");
//		String emailDomain = request.getParameter("emaildomain");
//		
//		MemberDto memberdto = new MemberDto();
//		memberdto.setUserId(userId);
//		memberdto.setUserName(userName);
//		memberdto.setUserPwd(userPwd);
//		memberdto.setEmailDomain(emailDomain);
//		memberdto.setEmailId(emailId);
//
//		
//		try {
//			int cnt = memberService.joinMember(memberdto);
//			//요청처리 정상 처리 : 메인화면으로 가기
//			if(cnt > 0) {
//				return "/index.jsp";
//			}
//			
//		} catch (Exception e) {
//			//처리 실패하는 경우 에러페이지로 이동
//			e.printStackTrace();
//			request.setAttribute("msg", "회원가입 중 에러 발생!");
//			return "/error/error.jsp";
//		}
//		
//		return "/index.jsp";
//		
//	}
//	
//	private String login(HttpServletRequest request, HttpServletResponse response) {
//		
//		String userId = request.getParameter("userid");
//		String userPwd = request.getParameter("userpwd");
//		
//		try {
//			// memberDto는 userid와 username을 알고있음.
//			MemberDto memberDto = memberService.loginMember(userId, userPwd);
//			if(memberDto != null) {		//로그인 성공
//				// 세션 설정
//				// 세션 객체 생성(기본 30분 유지)
//				HttpSession session = request.getSession();
//				session.setAttribute("userinfo", memberDto);
//				
//				// cookie 설정 - id 저장 체크 여부 확인하여 id 화면에 뿌려주기
//				// 체크 박스 값 가져오기 (name = "saveid"의 value값
//				String idsaver = request.getParameter("saveid");
//				if("ok".equals(idsaver)) {	//아이디 저장을 체크 했으면
//					Cookie cookie = new Cookie("ssafy_id", userId);
//					cookie.setPath(request.getContextPath());
//					cookie.setMaxAge(60*60*24*365*40);
//					response.addCookie(cookie);
//				} else {		// 아이디 저장을 해제한 경우
//					// 요청에서 쿠키 받기
//					Cookie cookies[] = request.getCookies();
//					if(cookies!=null) {
//						for(Cookie cookie : cookies) {
//							if("ssafy_id".equals(cookie.getName())) {
//								// 쿠키 삭제를 위해 쿠키의 expire 0으로 세팅
//								cookie.setMaxAge(0);
//								// 응답 내보낼때 유효기간 0인 쿠키 보내서 알려주기
//								// --> 확인하고 브라우저가 삭제함.
//								response.addCookie(cookie);
//								break;
//							}
//						}
//					}
//				}
//				
//				request.setAttribute("msg", "로그인 성공");
//				return "/index.jsp";
//			} else {
//				request.setAttribute("msg", "아이디 또는 비밀번호를 다시 확인해주세요.");
//				return "/index.jsp";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "로그인 중 에러 발생!");
//			return "/error/error.jsp";
//		}
//	}
//	
//	private String logout(HttpServletRequest request, HttpServletResponse response) {
//		//세션 삭제하기 --> 모두 삭제 invalidate()
//		HttpSession session = request.getSession();
//		session.invalidate();
//		return "/index.jsp";
//	}
//	
//	private String search(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		
//		try {
//			String res = memberService.searchPwd(userId);
//			System.out.println(res);
//			request.setAttribute("msg", "회원님의 비밀번호는 : "+res);
//		} catch (Exception e) {
//			//처리 실패하는 경우 에러페이지로 이동
//			e.printStackTrace();
//			request.setAttribute("msg", "탈퇴 중 에러 발생!");
//			return "/error/error.jsp";
//		}
//		
//		return "/index.jsp";
//	}
//
//	private String del(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//
//		try {
//			int cnt = memberService.delMember(userId);
//			//요청처리 정상 처리 : 메인화면으로 가기
//			if(cnt > 0) {
//				logout(request, response);
//				return "/index.jsp";
//			}
//		} catch (Exception e) {
//			//처리 실패하는 경우 에러페이지로 이동
////			e.printStackTrace();
//			request.setAttribute("msg", "탈퇴 중 에러 발생!");
//			return "/error/error.jsp";
//		}
//		return "/index.jsp";
//	}
//
//}
