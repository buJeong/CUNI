package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Article;
import com.example.demo.dto.ArticleReply;

@Mapper
public interface ArticleDao {

	List<Article> getList(Map<String, Object> param);

	Article getOne(Map<String, Object> param);

	List<ArticleReply> getReplies(Map<String, Object> param);

	long doAddReply(Map<String, Object> param);

	void doModifyReply(Map<String, Object> param);

	ArticleReply getOneReply(Map<String, Object> param);

	void doDeleteReply(Map<String, Object> param);

	long doAddArticle(Map<String, Object> param);

	long getNewArticleId();

	void doArticleModify(Map<String, Object> param);

	void doDeleteArticle(Map<String, Object> param);

	void doDeleteReplies(Map<String, Object> param);

	int getCount(Map<String, Object> args);

}
