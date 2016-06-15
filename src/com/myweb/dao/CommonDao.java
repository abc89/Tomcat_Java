package com.myweb.dao;

import java.util.*;
import java.sql.*;

import org.apache.tomcat.util.bcel.classfile.ClassFormatException;

import com.databases.DBConnectionManager;
import com.myweb.bean.AdminBean;
import com.myweb.bean.Bean;
import com.myweb.bean.CommonBean;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;
public class CommonDao extends Dao{
	  private String TABLENAME="common";
	     private String ID="common_ID";
	     private String USERNAME="common_Username";
	     private String PASSWORD="common_Password";
	     private String NAME="common_Name";
	     private String SEX="common_Sex";
	     private String TEL="common_Tel";
	     private String DEC="common_Dec";
	  // 验证登录
	     public boolean hasOne(String username, String password) {
	 	   return super.hasOne(username, password);
	 	}


	 	/**
	 	 * 
	 	 * @param where
	 	 * @param value
	 	 * @return List<CommonBean>
	 	 */
	 	public List<CommonBean> findList(String where, String value) {
	 		String sql = "select * from "+TABLENAME;
	 		sql+=" WHERE "+where+"='"+value+"'";
	 		Statement stat = null;
	 		ResultSet rs = null;
	 		Connection conn = DBConnectionManager.getInstance().getConnection();
	 		List<CommonBean> list = new ArrayList<CommonBean>();
	 		try {
	 			stat = conn.createStatement();
	 			rs = stat.executeQuery(sql);
	 			while (rs.next()) {
	 				CommonBean cnbean = new CommonBean();
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
	 	public CommonBean createBean(int id) {
	 		String sql = "select * from admin where "+ID+"=" + id;
	 		Statement stat = null;
	 		ResultSet rs = null;
	 		Connection conn = null;
	 		try {
	 			conn = DataBaseOperate.getConnection();
	 		} catch (DataBaseOperateException e1) {
	 			
	 			e1.printStackTrace();
	 		};
	 		CommonBean cnbean = new CommonBean();
	 		try {
	 			stat = conn.createStatement();
	 			rs = stat.executeQuery(sql);
	 			while (rs.next()) {
	 				cnbean=(CommonBean)this.builderBean(rs);
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
	 	public void Add(CommonBean cnbean) {
	 		String sql = "insert into "+TABLENAME+" (";
	 		sql += USERNAME+","+PASSWORD+","+NAME+","+SEX+","+TEL+","+DEC+","+NAME;
	 		sql += ") values(";
	 		sql += "'" + cnbean.getUserName() + "','"// + cnbean.getPassword()
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

	 	public boolean update(Bean bean) {
	 		if(bean.getType().compareTo(Bean.VIPUSER)!=0){
				throw new ClassFormatException();
			}
	 		CommonBean cnbean=(CommonBean)bean;
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

	 	// 删除
	 	public void Delete(String where,String value) {
	 		String sql = "delete "+TABLENAME+" where ";
	 		sql +=where+"="+value;
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


@Override
	 	protected String getHasOneSql(String password) {
	 		String sql = "select * from "+TABLENAME+" where "+USERNAME+"='" + USERNAME
	 				+ "' and "+PASSWORD+"='" + password + "'";
	 		return sql;
	 	}


		public boolean searchPasswordByUserName(String username,
				String password) {
			
			return this.hasOne(username, password);
		}


		@Override
		protected Bean builderBean(ResultSet rs) {
			super.hasBuilder(true);
			CommonBean bean=null;
			try {
				bean=new CommonBean();
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


	




}
