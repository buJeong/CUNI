package com.example.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Member;


@Mapper
public interface MemberDao {

	public Member doLogin(Map<String, Object> param);

	public Member getOne(long loginedMemberId);

	public long doJoin(Map<String, Object> param);

	public void doModify(Map<String, Object> param);

	public void doSecession(long loginedMemberId);

	public int DuplicateCheck(Map<String, Object> param);

	public void updateAuthStatus(Map<String, Object> param);

	public Member updateCheck(Map<String, Object> param);

	public Member doFindId(Map<String, Object> param);

	public Member doFindPw(Map<String, Object> param);
	
}
