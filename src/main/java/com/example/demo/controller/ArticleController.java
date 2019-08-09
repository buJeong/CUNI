package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("article/list")
	public String showList(Model model, @RequestParam Map<String, Object> param) {
		if ( param.containsKey("page")  == false ) {
			param.put("page", "1");
		}
		param.put("extra__repliesCount", true);
		Map<String, Object> pagedListRs = articleService.getPagedList(param);
		
		model.addAttribute("pagedListRs", pagedListRs);
		return "/article/list";
	}
	
	@RequestMapping("article/detail")
	public String showDetail(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
		Map<String, Object> rs = articleService.getOne(Maps.of("id", id));

		model.addAttribute("article", rs.get("article"));
		
		return "/article/detail";
	}
	
	@RequestMapping("article/getReplies")
	@ResponseBody
	public Map<String, Object> getReplies(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = articleService.getReplies(param);

		model.addAttribute("replies", rs.get("replies"));

		return rs;
	}
	
	@RequestMapping("article/addReply")
	@ResponseBody
	public Map<String, Object> addReply(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		
		param.put("memberId", loginedMemberId);
		
		Map<String, Object> rs = articleService.addReply(param);
		
		return rs;
	}
	
	@RequestMapping("article/doModifyReply")
	@ResponseBody
	public Map<String, Object> modifyReply(@RequestParam Map<String, Object> param, Model model) {
		
		Map<String, Object> rs = articleService.modifyReply(param);
		
		return rs;
	}
	
	@RequestMapping("article/doDeleteReply")
	@ResponseBody
	public Map<String, Object> deleteReply(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		
		Map<String, Object> rs = articleService.deleteReply(param);
		
		return rs;
	}
	
	@RequestMapping("article/add")
	public String showAddPage() {
		return "/article/add";
	}
	
	@RequestMapping("article/doAdd")
	public String addArticle(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		
		param.put("memberId", loginedMemberId);
		
		param.put("boardId", 1);
		
		Map<String, Object> rs = articleService.doAdd(param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("AS-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		return "/common/redirect";
	}
	
	@RequestMapping("article/modify")
	public String showModifyPage(@RequestParam Map<String, Object> param, Model model) {
		
		Map<String, Object> rs = articleService.getOne(param);
		
		model.addAttribute("article", rs.get("article"));
		
		return "/article/modify";
	}
	
	@RequestMapping("article/doModify")
	public String articleModify(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = articleService.doArticleModify(param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("AS-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		return "/common/redirect";
	}
	
	@RequestMapping("article/deleteArticle")
	public String deleteArticle(@RequestParam Map<String, Object> param, Model model) {
		System.out.println("==========================" + param);
		Map<String, Object> rs = articleService.doDeleteArticle(param);
		
		String resultCode = (String) rs.get("resultCode");
		
		String msg = (String) rs.get("msg");
		
		model.addAttribute("msg", msg);
		
		String locationReplaceUrl = (String) rs.get("locationReplaceUrl");
		
		Boolean historyBack = (Boolean) rs.get("historyBack");
		
		if ( resultCode.startsWith("AS-") ) {
			model.addAttribute("locationReplaceUrl", locationReplaceUrl);
		} else {
			model.addAttribute("historyBack", historyBack);
		}
		return "/common/redirect";
	}
}
