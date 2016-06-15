package com.myweb.dao;

import java.util.*;
import java.sql.*;

import org.apache.tomcat.util.bcel.classfile.ClassFormatException;

import com.databases.DBConnectionManager;
import com.myweb.bean.AdminBean;
import com.myweb.bean.Bean;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;

/**
 * 管理员数据类型
 * 对于该类 中生成的 bean子类 默认 adminBean
 * @author e7691
 *
 */
public class AdminDao extends Dao{
	//管理员 表 键名
	 
     private String TABLENAME="admin";
     private String ID="admin_ID";
     private String USERNAME="admin_User_Name";
     private String PASSWORD="admin_Password";
     private String NAME="admin_Name";
     private String SEX="admin_Sex";
     private String TEL="admin_Tel";
     private String DEC="admin_Dec";
	public AdminDao(){
		System.out.println("adminDao");
	}
	// 验证登录
	public boolean hasOne(String username, String password) {
	   return super.hasOne(username, password);
	}


	// 获取列表
	public List<AdminBean> finsList(String strwhere, String strorder) {
		String sql = "select * from "+TABLENAME;
		if (!(isInvalid(strwhere))) {
			sql += " where " + strwhere;
		}
		if (!(isInvalid(strorder))) {
			sql += " order by " + strorder;
		}
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = DBConnectionManager.getInstance().getConnection();
		List<AdminBean> list = new ArrayList<AdminBean>();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				AdminBean cnbean = new AdminBean();
				cnbean.setID(rs.getInt(ID));
				cnbean.setUserName(rs.getString(USERNAME));
			//	cnbean.setPassword(rs.getString(PASSWORD));
				cnbean.setSex(rs.getString(SEX));
				cnbean.setTel(rs.getString(TEL));
				cnbean.setDec(rs.getString(DEC));
				cnbean.setName(rs.getString(NAME));
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					DBConnectionManager.getInstance().freeConnection(conn);
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 
	 * @param id 管理人员id
	 * @return 返回管理人员 数据模型
	 */
	public AdminBean createBean(int id) {
		String sql = "select * from "+TABLENAME+" where "+ID+"=" + id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DataBaseOperate.getConnection();
		} catch (DataBaseOperateException e1) {
			
			e1.printStackTrace();
		};
		AdminBean cnbean = new AdminBean();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
			cnbean=(AdminBean)this.builderBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					DBConnectionManager.getInstance().freeConnection(conn);
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnbean;
	}

	// 添加
	public void Add(AdminBean cnbean) {
		String sql = "insert into "+TABLENAME+" (";
		sql += USERNAME+","+PASSWORD+","+NAME+","+SEX+","+TEL+","+DEC+","+NAME;
		sql += ") values(";
		sql += "'" + cnbean.getUserName() + "','" //+ cnbean.getPassword()
				+ "','" + cnbean.getSex() + "','" + cnbean.getTel() + "'"+ cnbean.getDec() + "'"+ cnbean.getName() + "'";
		sql += ")";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = DBConnectionManager.getInstance().getConnection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					DBConnectionManager.getInstance().freeConnection(conn);
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 修改
	public boolean update(Bean bean) {
		if(bean.getType().compareTo(Bean.VIPUSER)!=0){
			throw new ClassFormatException();
		}
		AdminBean cnbean=(AdminBean)bean;
		String sql = "update "+TABLENAME+" set ";
		sql += USERNAME+"='" + cnbean.getUserName() + "',";
		//sql += PASSWORD+"='" + cnbean.getPassword() + "',";
		sql += SEX+"='" + cnbean.getSex() + "',";
		sql += TEL+"='" + cnbean.getTel() + "'";
		sql += DEC+"='" + cnbean.getDec() + "'";
		sql += NAME+"='" + cnbean.getName() + "'";
		sql += " where "+ID+"='" + cnbean.getID() + "'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = DBConnectionManager.getInstance().getConnection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					DBConnectionManager.getInstance().freeConnection(conn);
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	
	// 判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

//	
//	String sql = "select * from "+TABLENAME+" where "+USERNAME+"='" + username
	//		+ "' and "+PASSWORD+"='" + password + "'";
	@Override
	protected String getHasOneSql(String password) {
		
		String sql = "select * from "+TABLENAME+" where "+PASSWORD+"='" + password + "'";
		System.out.println(sql);
		return sql;
	}


	@Override
	protected Bean builderBean(ResultSet rs) {
		super.hasBuilder(true);
		AdminBean bean=null;
		try {
			bean=new AdminBean();
			bean.setUserName(rs.getString(USERNAME));
	//	cnbean.setPassword(rs.getString(PASSWORD));
			bean.setSex(rs.getString(SEX));
			bean.setTel(rs.getString(TEL));
			bean.setDec(rs.getString(DEC));
			bean.setName(rs.getString(NAME));
			bean.setID(rs.getInt(ID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}


	/***
	 * 
	 */
	@Override
	public Bean getBean() {
		Bean bean=super.getBean();
		if(bean==null){
			throw new NullPointerException();
		}
		return bean;
	}











}
