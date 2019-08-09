package com.example.demo.service;

import java.util.Map;

public interface ArticleService {

	Map<String, Object> getList(Map<String, Object> param);

	Map<String, Object> getOne(Map<String, Object> param);

	Map<String, Object> addReply(Map<String, Object> param);

	Map<String, Object> modifyReply(Map<String, Object> param);

	Map<String, Object> deleteReply(Map<String, Object> param);

	Map<String, Object> doAdd(Map<String, Object> param);

	Map<String, Object> doArticleModify(Map<String, Object> param);

	Map<String, Object> doDeleteArticle(Map<String, Object> param);

	Map<String, Object> getReplies(Map<String, Object> param);

	Map<String, Object> getPagedList(Map<String, Object> param);

}
