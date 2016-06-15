package com.myweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.myweb.bean.AdminBean;
import com.myweb.bean.Bean;
import com.myweb.bean.CommentBean;


/**
 * 用户评论 数据库操作
 * @author e7691
 *
 */
public class CommentDao extends Dao {
	//评论id 映射 用户id
	private String TABLENAME="comment";
    private String ID="comment_ID";
    private String COMMENT_USER_ID="comment_User_ID";
    private String COMMENT_CONTENT="comment_Content";
	public void updateComment(CommentBean commentBean) {
		
	}

	@Override
	protected Bean builderBean(ResultSet rs) {
		super.hasBuilder(true);
		AdminBean bean=null;
		try {
			bean=new AdminBean();
			bean.setUserName(rs.getString(ID));
	//	cnbean.setPassword(rs.getString(PASSWORD));
			bean.setSex(rs.getString(COMMENT_USER_ID));
			bean.setTel(rs.getString(COMMENT_CONTENT));
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
	public boolean hasOne(String key, String value) {
		return super.hasOne(key, value);
	}

	@Override
	/**
	 * @key 评论id
	 * @calue id值
	 */
	protected String getHasOneSql(String value) {
		String sql = "select * from "+TABLENAME+" where "+ID+"='" + value
				+ "'";
		System.out.println(sql);
		return sql;
	}


	
}
