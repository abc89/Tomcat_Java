package com.myweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.bean.AdminBean;
import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.bean.Bean;
import com.myweb.bean.JDBean;
import com.myweb.dao.impl.ArticleDaoImpl;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;

/***
 * 文章存储
 * 
 * @author e7691
 * 
 */
public class ArticleDao implements ArticleDaoImpl {
	private String TABLENAME = "article";
	private String ID = "article_ID";
	private String ARTICLECONTENT = "article_Content";
	private String ARTICLETITLE = "article_Title";
	private String ADMINID = "admin_ID";
	private String ARTICLEDEC = "article_DEc";
    private DataBaseOperate baseOperate=DataBaseOperate.getInstance();
	/**
	 * builder a bean by database ResultSet
	 * 
	 * @param rs
	 *            java.sql.ResultSet
	 * @return bean instance of ArticleBean
	 */
	protected Bean builderBean(ResultSet rs) {
		ArticleBean bean = null;
		try {
			bean = new ArticleBean();
			bean.configBean(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public ArticleBean getArticle(String id) {
		ArticleBean bean = new ArticleBean();
		String sql = bean.getWhereIDSql(id);
		
		try {
			
			ResultSet rs =baseOperate.select(sql);
			if (rs.next()) {
				bean = (ArticleBean) this.builderBean(rs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return bean;
	}

	public ArticleList getAllDec() {
		ArticleList articleList = new ArticleList();
		String sql = "select * from " + TABLENAME;		
		try {
			ResultSet rs = baseOperate.select(sql);
			while (rs.next()) {
				ArticleBean bean = (ArticleBean) this.builderBean(rs);
				articleList.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return articleList;
	}

	/**
	 * 添加新文章
	 * *
	 * @param userID **
	 * 
	 */
	@Override
	public void insertArticle(String titleT, String decT, String contentT, String userID) {
		String sql = "insert into " + TABLENAME + " (";
		sql += ARTICLETITLE + "," + ARTICLEDEC + "," + ARTICLECONTENT+ "," + ADMINID ;
		sql += ") values(";
		sql += "'" + titleT + "','" + decT + "','"
				+ contentT + "','"+userID +"'";
		sql += ")";
		System.out.println(sql);
		baseOperate.execute(sql);
	
	}

}
