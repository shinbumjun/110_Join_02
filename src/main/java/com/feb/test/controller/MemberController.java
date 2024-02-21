package com.feb.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	public MemberController() {
		System.out.println("111111111111111111111111111");
	}
	
	// 회원가입 페이지
	@GetMapping("/joinPage.do")
	public ModelAndView loginForm() {
		System.out.println("222222222222222222222222222222");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	/* 
   		회원가입 
    	브라우저 name = FirstID, LastID, Email, password -> HashMap<String, String> params 
    	
    	FirstID+LastID -> member_name DB칼럼
		password-> passwd DB칼럼
		Email -> email DB칼럼
		
	*/
	@PostMapping("/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println("FirstID : " + params.get("FirstID")); // FirstID : shin
		System.out.println("LastID : " + params.get("LastID")); // LastID : bumjun
		System.out.println("Email : " + params.get("email")); // Email : sinbumjun123@naver.com
		System.out.println("password : " + params.get("password")); // password : 1512
		
		int result = memberService.join(params, request);
		
		if(result==1) {
			mv.addObject("resultMsg", "회원가입 성공");
		}else {
			mv.addObject("resultMsg", request.getAttribute("errorMessage")); // result는 에러 메시지
	        // -1이 반환될 때의 추가적인 처리 가능
		}
			
		mv.setViewName("home");
		return mv; // home.jsp
	}
}





