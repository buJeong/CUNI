package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("member/login")
	public String showLoginPage() {
		return "/member/login";
	}
	
	@RequestMapping("member/doLogin")
	public String login(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		
		Map<String, Object> rs = memberService.doLogin(param);
		
		long loginedMemberId = (long) rs.get("loginedMemberId");
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		if ( resultCode.startsWith("S-") ) {
			session.setAttribute("loginedMemberId", loginedMemberId);
			
			model.addAttribute("locationReplaceUrl", "/member/myPage");
		} else {
			model.addAttribute("historyBack", true);
		}
		return "/common/redirect";
	}
	
	@RequestMapping("member/doLogout")
	public String logout(HttpSession session, Model model) {
		session.removeAttribute("loginedMemberId");
		model.addAttribute("locationReplaceUrl", "/");
		return "/common/redirect";
	}
	
	@RequestMapping("member/myPage")
	public String showMyPage(HttpSession session, Model model) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "/member/myPage";
	}
	
	@RequestMapping("member/join")
	public String showJoinPage() {
		return "/member/join";
	}
	
	@RequestMapping("member/doJoin")
	public String join(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.doJoin(param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("S-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		
		return "/common/redirect";
	}
	
	@RequestMapping("member/confirm")
	public String confirm(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.updateAuthStatus(param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("S-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		
		return "/common/redirect";
	}
	
	@RequestMapping("member/modify")
	public String showModifyPage(HttpSession session, Model model) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "/member/modify";
	}
	
	@RequestMapping("member/doModify")
	public String modify(@RequestParam Map<String,Object> param, HttpSession session, Model model) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		
		Member member = memberService.getOne(loginedMemberId);
		
		param.put("id", loginedMemberId);
		
		param.put("loginId", member.getLoginId());
		
		Map<String, Object> rs = memberService.doModify(param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("S-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		
		return "/common/redirect";
	}
	
	@RequestMapping("member/secessionMember")
	public String secession(HttpSession session, Model model, @RequestParam Map<String, Object> param) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		
		Member member = memberService.getOne(loginedMemberId);
		
		Map<String, Object> rs = memberService.doSecession(member, param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("S-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
			
			session.removeAttribute("loginedMemberId");
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		
		return "/common/redirect";
	}
	
	@RequestMapping("member/findLoginId")
	public String findId(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.findId(param);

		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("S-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		
		return "/common/redirect";
	}
	
	@RequestMapping("member/findLoginPw")
	public String findPw(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.findPw(param);

		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("S-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		
		return "/common/redirect";
	}
}
