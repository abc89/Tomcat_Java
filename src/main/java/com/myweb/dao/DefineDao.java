package com.myweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;




import javax.sql.DataSource;

import com.myweb.bean.Bean;
import com.myweb.dao.impl.DefineDaoImpl;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;
/**
 * 数据库操作
 * 填充 bean
 * @author e7691
 *
 */
public abstract class DefineDao implements DefineDaoImpl {	
	private Bean bean;	
	protected boolean hasBuilder=false;//true 结果集构建 bean
	protected boolean select(String sql){
		boolean ok=false;
		hasBuilder=false;
		System.out.println("sql"+sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds  = (DataSource)initContext.lookup("java:/comp/env/jdbc/MySQLDS");
			//ds = (DataSource)envContext.lookup("jdbc/TestDB");
		    conn = ds.getConnection();
		//	conn = DataBaseOperate.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				//子类补充功能，填充结果集 ，返回填充的bean
				bean=toBuilderBean(rs);
				if(bean!=null){
					hasBuilder=true;
			}
			}
		} catch (SQLException ex) {
		}

		return ok;
		
	}
	private Bean toBuilderBean(ResultSet rs) {
		hasBuilder=false;
		return builderBean(rs);
	}
	protected boolean isBuilder(){
		return hasBuilder;
	}
	/***
	 * 模板登陆验证
	 * 验证通过 自动生成对应 bean
	 * @param key 键
	 * @param value 值
	 */
	protected boolean hasOne(String key, String value) {
		boolean ok=false;
		String sql=getHasOneSql(key);
		System.out.println("sql"+sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
	    Context initContext = new InitialContext();
		DataSource ds  = (DataSource)initContext.lookup("java:/comp/env/jdbc/MySQLDS");
		conn = ds.getConnection();
		//	conn = DataBaseOperate.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {		
				 ok=definePassword(value,rs);
				 if(ok)
				bean=builderBean(rs);
				
			}
		} catch (SQLException ex) {
		}

		return ok;
	}
	protected abstract boolean definePassword(String value, ResultSet rs) throws SQLException;
	/**
	 * 根据结果集构建 子类dao 对应 数据类型 bean
	 * @param rs 数据库结果集
	 */
	protected abstract Bean builderBean(ResultSet rs) ;
	public abstract boolean update(Bean bean);
	/**
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return sql语句
	 */
    protected abstract String getHasOneSql(String password);
/**
 * 
 * @return bean
 * @throws NullPointerException
 */
	public  Bean getBean(){
		if(hasBuilder){
		return bean;
		}else{
			throw new NullPointerException("子类未构建 bean 或者 未设置 已构建方法");
		}
	}
	/**
	 * 
	 * @param b 子类是否实现 builderBean方法
	 */
    public void hasBuilder(boolean b) {
	  hasBuilder=b;
   }

}
