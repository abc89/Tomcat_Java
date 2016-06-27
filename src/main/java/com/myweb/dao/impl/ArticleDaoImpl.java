package com.myweb.dao.impl;

public interface ArticleDaoImpl {
	/***
	 * 添加新文章
	 * @param title 标题
	 * @param dec 描述
	 * @param content 内容
	 */
	void insertArticle(String titleT, String decT, String contentT,String userID);
}
