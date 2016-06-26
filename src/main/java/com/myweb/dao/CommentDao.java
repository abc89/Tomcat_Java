package com.myweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.myweb.bean.AdminBean;
import com.myweb.bean.Bean;
import com.myweb.bean.CommentBean;

/**
 * article commentDao
 * 
 * @author e7691
 * 
 */
public class CommentDao extends ArticleDao {
	// CommentBean artribute
	private String TABLENAME = "comment";
	private String ID = "comment_ID";
	private String COMMENT_USER_ID = "comment_User_ID";
	private String COMMENT_CONTENT = "comment_Content";

	public void updateComment(CommentBean commentBean) {

	}

	@Override
	protected Bean builderBean(ResultSet rs) {
		AdminBean bean = null;
		try {
			bean = new AdminBean();
			bean.setUserName(rs.getString(ID));
			bean.setSex(rs.getString(COMMENT_USER_ID));
			bean.setTel(rs.getString(COMMENT_CONTENT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	/***
	 * select sentence
	 * 
	 * @param key
	 * @param value
	 * @return sql String
	 */
	private String selectSqlSenetence(String key, String value) {
		String sql = "select * from " + TABLENAME + " where " + ID + "='"
				+ value + "'";
		System.out.println(sql);
		return sql;
	}

}
