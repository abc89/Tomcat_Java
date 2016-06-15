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

import org.apache.catalina.startup.PasswdUserDatabase;

import com.databases.DBConnectionManager;
import com.myweb.bean.Bean;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;

public abstract class Dao {
	private Bean bean;
	
	protected boolean hasBuilder=false;//是否结果集 构建国 bean
	/***
	 * 模板登陆验证
	 * 验证通过 自动生成对应 bean
	 * @param key 键
	 * @param value 值
	 */
	public boolean hasOne(String key, String value) {
		boolean ok=false;
		String sql=getHasOneSql(value);
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
			
				 ok=definePassword(value,rs.getString("admin_Password"));
				 if(ok)
				bean=builderBean(rs);
				
			}
		} catch (SQLException ex) {
		}

		return ok;
	}
	private boolean definePassword(String value, String string) {
		//确认有该用户，为避免非法输入。
		//简单字符串比较
		if(value.compareTo(string)==0){
			return true;
			
		}
		return false;
		
	}
	/**
	 * 根据结果集构建 子类dao 对应 数据类型 bean
	 * @param rs 数据库结果集
	 */
	protected abstract Bean builderBean(ResultSet rs) ;
//	/**
//	 * 
//	 * @param rs
//	 * @param keyNames 表 键名列表
//	 * @return 值列表
//	 * @throws NullPoitException
//	 */
//	protected  List<String> builderBean(ResultSet rs,String[] keyNames) {
//		if(keyNames==null){
//			throw new NullPointerException("构建数据类型 模板方法 导入 键名 数组 为空");
//		}
//		try {
//		List<String> lists=new ArrayList<String>();
//		int size=keyNames.length;
//		for(int i=0;i<size;i++){
//				lists.add(rs.getString(keyNames[i]));
//		}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
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
