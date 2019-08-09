package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.ArticleReply;
import com.example.demo.util.CUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleDao articleDao;
	private static final int LIST_ITEMS_COUNT_IN_A_PAGE = 5;;
	
	private List<Article> _getList(Map<String, Object> args) {
		int page = 1;
		if (args.containsKey("page")) {
			String pageStr = (String) args.get("page");
			page = Integer.parseInt(pageStr);
		}
		if (args.containsKey("extra__repliesCount") && (boolean) args.containsKey("extra__repliesCount") == true) {
			args.put("leftJoin__articleReply", true);
			args.put("groupBy__articleId", true);
		}

		args.put("limitOffset", LIST_ITEMS_COUNT_IN_A_PAGE * (page - 1));
		args.put("limit", LIST_ITEMS_COUNT_IN_A_PAGE);

		return articleDao.getList(args);
	}
	
	@Override
	public Map<String, Object> getPagedList(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();

		int totalItemsCount = _getCount(param);
		int lastPage = (int) Math.ceil(totalItemsCount / (double) ArticleServiceImpl.LIST_ITEMS_COUNT_IN_A_PAGE);

		rs.put("page", CUtil.getAsInt(param.get("page")));
		rs.put("lastPage", lastPage);

		List<Article> list = _getList(param);

		rs.put("list", list);

		return rs;
	}
	
	private int _getCount(Map<String, Object> args) {
		return articleDao.getCount(args);
	}
	@Override
	public Map<String, Object> getList(Map<String, Object> param) {
		if ( param.containsKey("extra__repliesCount") && param.containsKey("extra__repliesCount") == true ) {
			param.put("leftJoin__articleReply", true);
			param.put("groupBy__id", true);
		}
		
		List<Article> list = articleDao.getList(param);
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( list != null ) {
			resultCode = "AS-1";
			
			locationReplaceUrl = "/article/list";
			return Maps.of("resultCode", resultCode, "locationReplaceUrl", locationReplaceUrl, "list", list);
		} else {
			resultCode = "AF-1";
			
			msg = "리스팅에 실패하였습니다.";
			
			return Maps.of("resultCode", resultCode, "historyBack", historyBack, "msg", msg);
		}
	}

	@Override
	public Map<String, Object> getOne(Map<String, Object> param) {
		Article article = articleDao.getOne(param);
				
		return Maps.of("article", article);
	}

	@Override
	public Map<String, Object> addReply(Map<String, Object> param) {
		
		long addReplyId = articleDao.doAddReply(param);
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( addReplyId == 0 ) {
			resultCode = "AF-2";
			
			msg = "댓글저장에 실패했습니다.";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		} else {
			resultCode = "AS-2";
			
			msg = "댓글을 저장했습니다.";
			
			locationReplaceUrl = "/article/detail?id=" + param.get("articleId");
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		}
	}

	@Override
	public Map<String, Object> modifyReply(Map<String, Object> param) {
		articleDao.doModifyReply(param);
		
		String resultCode = "AS-3";
		
		String msg = "댓글을 수정했습니다.";
		
		return Maps.of("resultCode", resultCode, "msg", msg);
		
	}

	@Override
	public Map<String, Object> deleteReply(Map<String, Object> param) {
		articleDao.doDeleteReply(param);
		
		String resultCode = "AS-4";
		
		String msg = "댓글을 삭제했습니다.";
		
		String locationReplaceUrl = "/article/detail?id=" + param.get("articleId");
		
		return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
	}

	@Override
	public Map<String, Object> doAdd(Map<String, Object> param) {
		
		long passCheck = articleDao.doAddArticle(param);
		
		long newArticleId = 0;
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( passCheck == 0 ) {
			resultCode = "AF-5";
			
			msg = "게시물저장에 실패했습니다.";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		} else {
			resultCode = "AS-5";
			
			msg = "게시물을 저장했습니다.";
			
			newArticleId = articleDao.getNewArticleId();
			
			locationReplaceUrl = "/article/detail?id=" + newArticleId;
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		}
	}

	@Override
	public Map<String, Object> doArticleModify(Map<String, Object> param) {
		articleDao.doArticleModify(param);
		
		Article article = articleDao.getOne(param);
		
		String resultCode = "";
		
		String msg = "";
		
		String locationReplaceUrl = "";
		
		boolean historyBack = true;
		
		if ( !article.getTitle().equals(param.get("title")) || !article.getBody().equals(param.get("body")) ) {
			resultCode = "AF-6";
			
			msg = "게시물 수정에 실패했습니다.";
			
			return Maps.of("resultCode", resultCode, "msg", msg, "historyBack", historyBack);
		} else {
			resultCode = "AS-6";
			
			msg = "게시물을 수정했습니다.";
			
			locationReplaceUrl = "/article/detail?id=" + article.getId();
			
			return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
		}
	}

	@Override
	public Map<String, Object> doDeleteArticle(Map<String, Object> param) {
		articleDao.doDeleteArticle(param);
		
		articleDao.doDeleteReplies(param);
		
		String resultCode = "AS-7";
		
		String msg = "게시물을 삭제했습니다.";
		
		String locationReplaceUrl = "/article/list";
		
		return Maps.of("resultCode", resultCode, "msg", msg, "locationReplaceUrl", locationReplaceUrl);
	}

	@Override
	public Map<String, Object> getReplies(Map<String, Object> param) {
		
		List<ArticleReply> replies = articleDao.getReplies(param);
		
		
		return Maps.of("replies", replies);
	}
}
