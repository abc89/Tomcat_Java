package com.myweb.bean;

import java.util.ArrayList;
import java.util.List;

public class ArticleList {
	private static List<ArticleBean> articles = new ArrayList<ArticleBean>();

	public ArticleList() {
		articles.clear();
	}

	public static void add(ArticleBean bean) {
		articles.add(bean);
	}

	public static void detele(ArticleBean bean) {
		articles.remove(bean);
	}

	public static void update(ArticleBean bean) {
		String testID = bean.getId();
		int size = articles.size();
		for (int i = 0; i < size; i++) {
			ArticleBean articleBean = articles.get(i);
			String cur = articleBean.getId();
			boolean delete = testID.compareTo(cur) == 0 ? true : false;
			if (delete) {
				articles.remove(i);
				break;
			}
		}
	}

	public static void close(ArticleBean bean) {
		articles.clear();
		;
	}

	public int size() {
		return articles.size();
	}

	public ArticleBean getIndex(int index) {
		return articles.get(index);
	}
}
