package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.JwtServiceImpl;
import com.ssafy.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class MemberController {
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemberService memberService;

	@Autowired
	private JwtServiceImpl jwtService;

	@GetMapping()
	public String index() {
		return "index";
	}

	// idcheck
	@GetMapping("/idCheck/{userid}")
	@ResponseBody
	public ResponseEntity<?> idCheck(@PathVariable("userid") String userid) {
		try {

			// System.out.println(userid);
			int cnt = memberService.idCheck(userid);

			if (cnt > 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return exceptionHandling(e);

		}
	}

	// modify
	@PutMapping("/modify")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> userModify(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int cnt = memberService.modifyMember(memberDto);
			if (cnt > 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// join
	@PostMapping("/join")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> userRegister(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		System.out.println(memberDto);
		
		try {
			int cnt = memberService.joinMember(memberDto);
			if (cnt > 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// login page
	@GetMapping(value = "/mvlogin")
	public String loginPage() {
		return "/user/login";

	}

//	// login
//	@PostMapping("/login")
//	public String login(@RequestParam Map<String, String> map, HttpServletRequest request,
//			HttpServletResponse response) {
//		try {
//
//			String userId = map.get("userid");
//			String userPwd = map.get("userpwd");
//
//			System.out.println(userId + " " + userPwd);
//
//			// memberDto는 userid와 username을 알고있음.
//			MemberDto member = memberService.loginMember(userId, userPwd);
//			if (member != null) { // 로그인 성공
//				// 세션 설정
//				// 세션 객체 생성(기본 30분 유지)
//				HttpSession session = request.getSession();
//				session.setAttribute("userinfo", member);
//
//				// cookie 설정 - id 저장 체크 여부 확인하여 id 화면에 뿌려주기
//				// 체크 박스 값 가져오기 (name = "saveid"의 value값
//				String idsaver = map.get("saveid");
//
//				if ("ok".equals(idsaver)) { // 아이디 저장을 체크 했으면
//					Cookie cookie = new Cookie("ssafy_id", userId);
//					cookie.setPath(request.getContextPath());
//					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
//					response.addCookie(cookie);
//				} else { // 아이디 저장을 해제한 경우
//					// 요청에서 쿠키 받기
//					Cookie cookies[] = request.getCookies();
//					if (cookies != null) {
//						for (Cookie cookie : cookies) {
//							if ("ssafy_id".equals(cookie.getName())) {
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
//				System.out.println("session: " + session.getAttribute("userinfo"));
//				return "redirect:";
//
//			} else {
//
//				// 실패
//				return "redirect:";
//
//			}
//
//		} catch (Exception e) {
//			return "/error/error";
//		}
//
//	}

	// login
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.loginMember(memberDto);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());// key, data
				memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// logout
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// searchUser
	@GetMapping("/{userid}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> search(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			// memberDto는 userid와 username을 알고있음.
			MemberDto member = memberService.search(userid);
			System.out.println(userid);
			if (member != null) { // 로그인 성공
				resultMap.put("userInfo", member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			logger.error("유저 정보 가져오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// search - 비밀번호 찾기
	@GetMapping("/user/password/{userid}")
	@ResponseBody
	public ResponseEntity<?> searchPwd(@PathVariable("userid") String userId) {
		try {
			String res = memberService.searchPwd(userId);

			return new ResponseEntity<String>(res, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	// search - 비밀번호 찾기
//	@GetMapping("/user/{userid}")
//	@ResponseBody
//	public ResponseEntity<?> search(@PathVariable("userid") String userId) {
//		try {
//			String res = memberService.searchPwd(userId);
//
//			return new ResponseEntity<String>(res, HttpStatus.OK);
//
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

	// delete - 회원 탈퇴
	@DeleteMapping("/user/{userid}")
	@ResponseBody
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) {
		try {
			int cnt = memberService.delMember(userId);
			logger.info("delMember - 호출");
			System.out.println(cnt);
			if (cnt > 0) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
