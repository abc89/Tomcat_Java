package com.myweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.databases.DBConnectionManager;
import com.myweb.bean.AdminBean;
import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.bean.Bean;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;

public class ArticleDao extends Dao {
	     private String TABLENAME="article";
	     private String ID="article_ID";
	     private String ARTICLECONTENT="article_Content";
	     private String ARTICLETITLE="article_Title";
	     private String ADMINID="admin_ID";
	     private String ARTICLEDEC="article_DEc";
	@Override
	public boolean hasOne(String key, String value) {
		// TODO Auto-generated method stub
		return super.hasOne(key, value);
	}

	@Override
	protected Bean builderBean(ResultSet rs) {
		super.hasBuilder(true);
		ArticleBean bean=null;
		try {
			bean=new ArticleBean();
		    bean.configBean(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public boolean update(Bean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getHasOneSql( String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArticleBean getArticle(String id) {
		ArticleBean bean=new ArticleBean();
		String sql = bean.getWhereIDSql(id);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			   DataSource ds  = (DataSource)initContext.lookup("java:/comp/env/jdbc/MySQLDS");
			//ds = (DataSource)envContext.lookup("jdbc/TestDB");
		conn = ds.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		};
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
		      bean=(ArticleBean)this.builderBean(rs);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					 conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}

	public ArticleList getAllDec() {
		ArticleList articleList=new ArticleList();
		String sql = "select * from "+TABLENAME;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			   DataSource ds  = (DataSource)initContext.lookup("java:/comp/env/jdbc/MySQLDS");
			//ds = (DataSource)envContext.lookup("jdbc/TestDB");
		conn = ds.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		};
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
		     ArticleBean bean=(ArticleBean)this.builderBean(rs);
			articleList.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
                   conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return articleList;
	}

}
