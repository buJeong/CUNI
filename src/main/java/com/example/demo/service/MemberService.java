package com.example.demo.service;

import java.util.Map;

import com.example.demo.dto.Member;

public interface MemberService {

	Map<String, Object> doLogin(Map<String, Object> param);

	Member getOne(long loginedMemberId);

	Map<String, Object> doJoin(Map<String, Object> param);

	Map<String, Object> doModify(Map<String, Object> param);

	Map<String, Object> doSecession(Member member, Map<String, Object> param);

	Map<String, Object> updateAuthStatus(Map<String, Object> param);

	Map<String, Object> findId(Map<String, Object> param);

	Map<String, Object> findPw(Map<String, Object> param);
	
}
