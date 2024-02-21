package com.feb.test.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

	public int join(HashMap<String, String> params);

	// isMailSecure에서 이메일 중복을 체크하기 위한 메서드
	public int Emailcheck(String email);
}
