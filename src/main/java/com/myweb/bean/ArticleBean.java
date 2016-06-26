package com.myweb.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleBean extends Bean {
	/**
	 * ���±�ṹ
	 */
	private static String TABLENAME = "article";
	private static String ID = "article_ID";
	private static String ARTICLECONTENT = "article_Content";
	private static String ARTICLETITLE = "article_Title";
	private static String ADMINID = "admin_ID";// ��� ���������û�ID
	private static String ARTICLEDEC = "article_DEc";

	private String id;
	private String title;
	private String dec;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArticleBean() {

	}

	/**
	 * 
	 * @param id
	 *            �û�id
	 * @param fenShu
	 *            �û����ַ���
	 * @param content
	 *            �û���������
	 * @throws SQLException
	 */
	public void configBean(ResultSet rs) throws SQLException {
		setId(rs.getString(ID));
		setContent(rs.getString(ARTICLECONTENT));
		setDec(rs.getString(ARTICLEDEC));
		setTitle(rs.getString(ARTICLETITLE));
	}

	private void show() {
		System.out.println(id + dec);
	}

	public String getType() {
		// TODO Auto-generated method stub
		return Bean.ARTICLE;
	}

	public String getWhereIDSql(String id2) {
		String sql = "select * from " + TABLENAME + " where " + ID + "=" + id2;
		return sql;
	}
}
