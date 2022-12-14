package com.ssafy.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import com.ssafy.apt.AptSearchDto;
import com.ssafy.member.model.FavoriteCardDto;
import com.ssafy.member.model.FavoriteDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.FavoriteService;
import com.ssafy.member.model.service.FavoriteServiceImpl;
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

	@Autowired
	private FavoriteService favoriteService;

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
//			// memberDto??? userid??? username??? ????????????.
//			MemberDto member = memberService.loginMember(userId, userPwd);
//			if (member != null) { // ????????? ??????
//				// ?????? ??????
//				// ?????? ?????? ??????(?????? 30??? ??????)
//				HttpSession session = request.getSession();
//				session.setAttribute("userinfo", member);
//
//				// cookie ?????? - id ?????? ?????? ?????? ???????????? id ????????? ????????????
//				// ?????? ?????? ??? ???????????? (name = "saveid"??? value???
//				String idsaver = map.get("saveid");
//
//				if ("ok".equals(idsaver)) { // ????????? ????????? ?????? ?????????
//					Cookie cookie = new Cookie("ssafy_id", userId);
//					cookie.setPath(request.getContextPath());
//					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
//					response.addCookie(cookie);
//				} else { // ????????? ????????? ????????? ??????
//					// ???????????? ?????? ??????
//					Cookie cookies[] = request.getCookies();
//					if (cookies != null) {
//						for (Cookie cookie : cookies) {
//							if ("ssafy_id".equals(cookie.getName())) {
//								// ?????? ????????? ?????? ????????? expire 0?????? ??????
//								cookie.setMaxAge(0);
//								// ?????? ???????????? ???????????? 0??? ?????? ????????? ????????????
//								// --> ???????????? ??????????????? ?????????.
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
//				// ??????
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
				logger.debug("????????? accessToken ?????? : {}", accessToken);
				logger.debug("????????? refreshToken ?????? : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("????????? ?????? : {}", e);
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
			logger.error("???????????? ?????? : {}", e);
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
			// memberDto??? userid??? username??? ????????????.
			MemberDto member = memberService.search(userid);
			System.out.println(userid);
			if (member != null) { // ????????? ??????
				resultMap.put("userInfo", member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			logger.error("?????? ?????? ???????????? ?????? : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// search - ???????????? ??????
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

//	// search - ???????????? ??????
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

	// delete - ?????? ??????
	@DeleteMapping("/{userid}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> userDelete(@PathVariable("userid") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("delMember - ??????");

		try {
			int cnt = memberService.delMember(userId);

			if (cnt > 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ???????????? ????????????
	@GetMapping("/favorite/{userid}/{aptCode}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getFavorite(@PathVariable("userid") String userId,
			@PathVariable("aptCode") String aptCode) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		System.out.println("???????????? ????????????");
		System.out.println(userId + " " + aptCode);
		try {
			FavoriteDto favoriteDto = new FavoriteDto(Long.parseLong(aptCode), userId);

			FavoriteDto result = favoriteService.getFavorite(favoriteDto);

			System.out.println(result);
			
			if (result != null) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ???????????? ??????
	@PostMapping("/favorite")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> addFavorite(@RequestBody FavoriteDto favoriteDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		System.out.println("?????? ?????? ??????!   " + favoriteDto);
		
		try {
			int cnt = favoriteService.addFavorite(favoriteDto);

			if (cnt > 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ???????????? ??????
	@DeleteMapping("/favorite")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteFavorite(@RequestBody FavoriteDto favoriteDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			int cnt = favoriteService.deleteFavorite(favoriteDto);

			if (cnt > 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ???????????? ????????????
		@GetMapping("/favorite/{userid}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> getFavorite(@PathVariable("userid") String userId) {
			Map<String, Object> resultMap = new HashMap<>();
			HttpStatus status = null;
			
			try {
				ArrayList<FavoriteCardDto> list = (ArrayList<FavoriteCardDto>)favoriteService.getFavoriteInfo(userId);
				
				System.out.println(list);
				
				if (list != null) {
					resultMap.put("favoriteInfo", list);
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", FAIL);
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("message", FAIL);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}

			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
}
