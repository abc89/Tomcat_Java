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
	
	protected boolean hasBuilder=false;//�Ƿ����� ������ bean
	/***
	 * ģ���½��֤
	 * ��֤ͨ�� �Զ����ɶ�Ӧ bean
	 * @param key ��
	 * @param value ֵ
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
		//ȷ���и��û���Ϊ����Ƿ����롣
		//���ַ����Ƚ�
		if(value.compareTo(string)==0){
			return true;
			
		}
		return false;
		
	}
	/**
	 * ���ݽ�������� ����dao ��Ӧ �������� bean
	 * @param rs ���ݿ�����
	 */
	protected abstract Bean builderBean(ResultSet rs) ;
//	/**
//	 * 
//	 * @param rs
//	 * @param keyNames �� �����б�
//	 * @return ֵ�б�
//	 * @throws NullPoitException
//	 */
//	protected  List<String> builderBean(ResultSet rs,String[] keyNames) {
//		if(keyNames==null){
//			throw new NullPointerException("������������ ģ�巽�� ���� ���� ���� Ϊ��");
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
	 * @param username �û���
	 * @param password ����
	 * @return sql���
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
			throw new NullPointerException("����δ���� bean ���� δ���� �ѹ�������");
		}
	}
	/**
	 * 
	 * @param b �����Ƿ�ʵ�� builderBean����
	 */
public void hasBuilder(boolean b) {
	hasBuilder=b;
}

}
