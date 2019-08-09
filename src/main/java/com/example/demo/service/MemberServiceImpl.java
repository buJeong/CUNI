package com.example.demo.service;

import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.TempKey;
import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Member;
import com.example.demo.handler.MailHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public Map<String, Object> doLogin(Map<String, Object> param) {
		
		Member member = (Member) memberDao.doLogin(param);
		
		long loginedMemberId = 0;
		
		String resultCode = "";
		
		String msg = "";
		
		if ( member != null ) {
			if ( member.getDelStatus() == 0 ) {
				loginedMemberId = member.getId();
				
				resultCode = "S-1";
				
				msg = "로그인되었습니다.";
				
				return Maps.of("loginedMemberId", loginedMemberId, "resultCode", resultCode, "msg", msg);
			} else {
				resultCode = "F-0";
				
				msg = "탈퇴한 회원입니다.";
				
				return Maps.of("loginedMemberId", loginedMemberId, "resultCode", resultCode, "msg", msg);
			}
		} else {
			resultCode = "F-1";
			
			msg = "일치하는 회원이 없습니다.";
			
			return Maps.of("loginedMemberId", loginedMemberId, "resultCode", resultCode, "msg", msg);
		}
	}

	@Override
	public Member getOne(long loginedMemberId) {
		
		return memberDao.getOne(loginedMemberId);
	}

	@Override
	public Map<String, Object> doJoin(Map<String, Object> param) {
		int value = memberDao.DuplicateCheck(param);
		
		long joinMemberId = 0;
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( value == 0 ) {
			param.put("authKey", new TempKey().getKey());
			
			joinMemberId = memberDao.doJoin(param);
			
			MailHandler mail;
			try {
				mail = new MailHandler(sender);
				mail.setFrom("js_WEB", "buJeong");
				mail.setTo((String)param.get("email"));
				mail.setSubject("인증메일입니다.");
				mail.setText(new StringBuffer().append("<h1>이메일인증</h1>")
						.append("<a href='http://localhost:8090/member/confirm?email=").append((String)param.get("email"))
						.append("&authKey=").append((String)param.get("authKey"))
						.append("' target='_blank'> 누르시면 메일 인증 페이지로 이동합니다. </a>").toString()
						);
				mail.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultCode = "S-2";
			
			msg = "가입되었습니다.";
			
			locationReplaceUrl = "/member/login";
			
			return Maps.of("joinMemberId", joinMemberId, "resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		} else {
			resultCode = "F-2";
			
			msg = "가입에 실패하였습니다.";
			
			return Maps.of("joinMemberId", joinMemberId, "resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		}
	
	}

	@Override
	public Map<String, Object> doModify(Map<String, Object> param) {
		memberDao.doModify(param);
		
		Member modifyMember = memberDao.getOne((long)param.get("id"));
		
		long modifyMemberId = modifyMember.getId();
		
		String afterPw = modifyMember.getLoginPw();
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( modifyMemberId == (long) param.get("id") && afterPw.equals(param.get("afterPw")) ) {
						
			resultCode = "S-3";
			
			msg = "수정되었습니다.";
			
			locationReplaceUrl = "/member/myPage";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
			
		} else {
			resultCode = "F-3";
			
			msg = "수정되지 않았습니다.";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		}
	}

	@Override
	public Map<String, Object> doSecession(Member member, Map<String, Object> param) {
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( member.getLoginPw().equals(param.get("passwd")) ) {
			memberDao.doSecession(member.getId());
			
			resultCode = "S-4";
			
			msg = "탈퇴처리되었습니다.";
			
			locationReplaceUrl = "/member/main";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		} else {
			resultCode = "F-4";
			
			msg = "비밀번호가 일치하지 않습니다.";

			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		}
		
	}

	@Override
	public Map<String, Object> updateAuthStatus(Map<String, Object> param) {
		memberDao.updateAuthStatus(param);
		
		Member member = memberDao.updateCheck(param);
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( member != null ) {
			resultCode = "S-5";
			
			msg = "인증이 완료되었습니다.";
			
			locationReplaceUrl = "/member/login";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		} else {
			resultCode = "F-5";
			
			msg = "인증되지 않았습니다.";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		}
	}

	@Override
	public Map<String, Object> findId(Map<String, Object> param) {
		Member member = memberDao.doFindId(param);
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( member != null ) {
			MailHandler mail;
			try {
				mail = new MailHandler(sender);
				mail.setFrom("js_WEB", "buJeong");
				mail.setTo((String)param.get("email"));
				mail.setSubject("아이디찾기 응답메일입니다.");
				mail.setText(new StringBuffer().append("<h1>아이디</h1>")
						.append((String)member.getLoginId()).toString()
						);
				mail.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultCode = "S-6";
			
			msg = "인증하신 메일로 아이디를 보내드렸습니다.";
			
			locationReplaceUrl = "/member/login";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		} else {
			resultCode = "F-6";
			
			msg = "일치하는 회원이 없습니다.";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		}

	}
	
	@Override
	public Map<String, Object> findPw(Map<String, Object> param) {
		Member member = memberDao.doFindPw(param);
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( member != null ) {
			MailHandler mail;
			try {
				mail = new MailHandler(sender);
				mail.setFrom("js_WEB", "buJeong");
				mail.setTo((String)param.get("email"));
				mail.setSubject("비밀번호찾기 응답메일입니다.");
				mail.setText(new StringBuffer().append("<h1>비밀번호</h1>")
						.append((String)member.getLoginPw()).toString()
						);
				mail.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultCode = "S-7";
			
			msg = "인증하신 메일로 비밀번호를 보내드렸습니다.";
			
			locationReplaceUrl = "/member/login";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		} else {
			resultCode = "F-7";
			
			msg = "일치하는 회원이 없습니다.";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		}

	}
}
