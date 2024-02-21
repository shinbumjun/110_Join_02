package com.feb.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feb.test.dao.MemberDao;
import com.feb.test.util.Sha512Encoder;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;

	public int join(HashMap<String, String> params, HttpServletRequest request) {
		
		System.out.println("FirstID2 : " + params.get("FirstID")); // FirstID : shin
		System.out.println("LastID2 : " + params.get("LastID")); // LastID : bumjun
		System.out.println("Email2 : " + params.get("email")); // Email : sinbumjun123@naver.com
		System.out.println("passwd2 : " + params.get("passwd")); // password : 1512
		
		try {
	
			String firstId = params.get("FirstID");
		    String lastId = params.get("LastID");
		    
		    // FirstID와 LastID를 합쳐서 member라는 키로 값을 설정
		    String member = firstId + lastId;
		    params.put("memberId", member);
		    
		    // 비밀번호 암호화
		    Sha512Encoder encoder = Sha512Encoder.getInstance();
			String passwd = params.get("passwd");
			String encodeTxt = encoder.getSecurePassword(passwd);
			params.put("passwd", encodeTxt); // 암호화한 패쓰워드 추가 
			
			String email = params.get("email");
			if(!isMailSecure(email)) { // true면 에러를 던져준다
				throw new IllegalArgumentException("이미 사용 중인 이메일입니다."); 
			}
			return memberDao.join(params); // 이메일 값이 중복이 아닐 경우 DB에 저정
			
		} catch (IllegalArgumentException e) {  
			// 이메일이 중복이라서 오류가 나는거라 예외를 던져줌
			request.setAttribute("errorMessage", e.getMessage());
			return -1; // 에러면 -1를 던져준다
		}
	}

	// 이메일 중복을 체크하기 위한 메서드
	private boolean isMailSecure(String email) {
		int count = memberDao.Emailcheck(email); // int반환
		System.out.println("count111111111111111111 : " + count);
	    return count == 0; // email이 같은게 없으면 반환값이 0 true를 반환
	}
}
