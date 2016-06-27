package com.myweb.dao;

import java.util.*;
import java.sql.*;

import com.myweb.bean.AdminBean;
import com.myweb.bean.Bean;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;

/**
 * 管理员数据类型 对于该类 中生成的 bean子类 默认 adminBean
 * 
 * @author e7691
 * 
 */
public class AdminDao extends DefineDao {
	// 管理员 表 键名
	private String TABLENAME = "admin";
	private String ID = "admin_ID";
	private String USERNAME = "admin_User_Name";
	private String PASSWORD = "admin_Password";
	private String NAME = "admin_Name";
	private String SEX = "admin_Sex";
	private String TEL = "admin_Tel";
	private String DEC = "admin_Dec";
    private DataBaseOperate baseOperate=DataBaseOperate.getInstance();
	public AdminDao() {
		System.out.println("adminDao");
	}

	/**
	 * identify user
	 * @param userName the enter username
	 * @param passeord  the enter password
	 */
	@Override
	public boolean hasUser(String userName, String password) {
		super.hasOne(userName, password);
		return super.hasOne(userName, password);
	}


	/**
	 * 
	 * @param id
	 *            管理人员id
	 * @return 返回管理人员 数据模型
	 */
	public AdminBean createBean(int id) {
		String sql = "select * from " + TABLENAME + " where " + ID + "=" + id;
		AdminBean cnbean = new AdminBean();
		try {
			ResultSet rs = baseOperate.select(sql);
			while (rs.next()) {
				cnbean = (AdminBean) this.builderBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnbean;
	}

	

	/***
	 * modify table 
	 */
	public boolean update(Bean bean) {
		if (bean.getType().compareTo(Bean.VIPUSER) != 0) {
			throw new ClassCastException();
		}
		AdminBean cnbean = (AdminBean) bean;
		String sql = "update " + TABLENAME + " set ";
		sql += USERNAME + "='" + cnbean.getUserName() + "',";
		// sql += PASSWORD+"='" + cnbean.getPassword() + "',";
		sql += SEX + "='" + cnbean.getSex() + "',";
		sql += TEL + "='" + cnbean.getTel() + "'";
		sql += DEC + "='" + cnbean.getDec() + "'";
		sql += NAME + "='" + cnbean.getName() + "'";
		sql += " where " + ID + "='" + cnbean.getID() + "'";
		Boolean ok=baseOperate.execute(sql);
		return ok;
	}

	/***
	 * 
	 * @param value 数据库 键对应 值 验证可效性
	 * @return  true： 值有效
	 *          false： 非有效值 不符合格式
	 */
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	@Override
	protected String getHasOneSql(String password) {

		String sql = "select * from " + TABLENAME + " where " + PASSWORD + "='"
				+ password + "'";
		System.out.println(sql);
		return sql;
	}

	/***
	 * @param rs   call by parent class's template method
	 * @return Bean  AdminBean instance
	 */
	@Override
	protected Bean builderBean(ResultSet rs) {
		super.hasBuilder(true);
		AdminBean bean = null;
		try {
			bean = new AdminBean();
			bean.setUserName(rs.getString(USERNAME));
			// cnbean.setPassword(rs.getString(PASSWORD));
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
	 * @return  a AdminBean
	 * @throws NullPointerException if AdminBean  have not been created before
	 */
	@Override
	public Bean getBean() {
		Bean bean = super.getBean();
		if (bean == null) {
			throw new NullPointerException();
		}
		return bean;
	}
    /***
     * @param recPassword the user-entered password
     * @param rs select admin information from admin table by the user-entered username
     * @return boolean  true:Verify password through
     *                  false: password error or username error lead to rs is null
     */
	@Override
	protected boolean definePassword(String recPassword, ResultSet rs)
			throws SQLException {
		// 确认有该用户，为避免非法输入。
		// 简单字符串比较
		String truePassword = rs.getString("admin_User_Password");
		if (rs!=null&&recPassword.compareTo(truePassword) == 0) {
			return true;

		}
		return false;
	}

}
