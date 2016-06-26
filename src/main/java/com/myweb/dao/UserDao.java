package com.myweb.dao;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

import com.myweb.bean.UserBean;
import com.myweb.bean.Bean;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;

/**
 * 管理员数据类型 对于该类 中生成的 bean子类 默认 adminBean
 * 
 * @author e7691
 * 
 */
public class UserDao extends DefineDao {
	// 管理员 表 键名

	private String TABLENAME = "user";
	private String ID = "id";
	private String USERNAME = "user_name";
	private String PASSWORD = "user_password";
	private String registState = "null";

	public UserDao() {
		System.out.println("userdao");
	}

	// 验证登录
	public boolean hasUser(String userName, String password) {
		super.hasOne(userName, password);
		return super.hasOne(userName, password);
	}

	// 获取列表
	public List<UserBean> finsList(String strwhere, String strorder) {
		String sql = "select * from " + TABLENAME;
		if (!(isInvalid(strwhere))) {
			sql += " where " + strwhere;
		}
		if (!(isInvalid(strorder))) {
			sql += " order by " + strorder;
		}
		Statement stat = null;
		ResultSet rs = null;
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			Connection conn = DataBaseOperate.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				UserBean cnbean = new UserBean();
				cnbean.setID(rs.getString(ID));
				cnbean.setUserName(rs.getString(USERNAME));
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @param id
	 *            管理人员id
	 * @return 返回管理人员 数据模型
	 */
	public UserBean createBean(int id) {
		String sql = "select * from " + TABLENAME + " where " + ID + "=" + id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		UserBean cnbean = new UserBean();
		try {
			conn = DataBaseOperate.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				cnbean = (UserBean) this.builderBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnbean;
	}

	// 添加
	public void Add(UserBean cnbean) {
		String sql = "insert into " + TABLENAME + " (";
		sql += USERNAME + "," + PASSWORD;
		sql += ") values(";
		sql += "'" + cnbean.getUserName() + "','" // + cnbean.getPassword()
				+ "','" + cnbean.getPassword() + "'";
		sql += ")";
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = DataBaseOperate.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 修改
	public boolean update(Bean bean) {
		if (bean.getType().compareTo(Bean.VIPUSER) != 0) {
			throw new ClassCastException();
		}
		UserBean cnbean = (UserBean) bean;
		String sql = "update " + TABLENAME + " set ";
		sql += USERNAME + "='" + cnbean.getUserName() + "',";
		// sql += PASSWORD+"='" + cnbean.getPassword() + "',";
		sql += " where " + ID + "='" + cnbean.getID() + "'";
		Statement stat = null;
		try {
			Connection conn = DataBaseOperate.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	/**
	 * 获取 sql语句
	 */
	@Override
	protected String getHasOneSql(String value) {

		String sql = "select * from " + TABLENAME + " where " + USERNAME + "='"
				+ value + "'";
		System.out.println(sql);
		return sql;
	}

	@Override
	protected Bean builderBean(ResultSet rs) {
		super.hasBuilder(true);
		UserBean bean = null;
		try {
			bean = new UserBean();
			bean.setUserName(rs.getString(USERNAME));
			bean.setID(rs.getString(ID));
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
		Bean bean = super.getBean();
		if (bean == null) {
			throw new NullPointerException();
		}
		return bean;
	}

	@Override
	protected boolean definePassword(String recPassword, ResultSet rs)
			throws SQLException {
		{
			// 确认有该用户，为避免非法输入。
			// 简单字符串比较
			String truePassword = rs.getString("user_password");
			if (recPassword.compareTo(truePassword) == 0) {
				return true;

			}
			return false;

		}
	}

	public boolean regist(String userName2, String password2) {
		if (!check(userName2, password2)) {
			return false;
		}
		String sql = "insert into " + TABLENAME + " (";
		sql += USERNAME + "," + PASSWORD;
		sql += ") values(";
		sql += "'" + userName2 + "','" + password2 + "'";
		sql += ")";
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = DataBaseOperate.getConnection();
			stat = conn.createStatement();

			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean check(String userName2, String password2) {
		String regex = "[0-9A-Za-z_]*";
		boolean ok = match(regex, password2);
		if (!ok) {
			registState = "输入错误  只允许需输入字母数字 " + userName2;
			return false;
		}
		boolean noHas = hasUsername(userName2);
		return noHas;
	}

	private boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher mac = pattern.matcher(str);
		return mac.matches();
	}

	private boolean hasUsername(String userName2) {
		String sql = "select * from " + TABLENAME + " where " + USERNAME + "='"
				+ userName2 + "'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;

		UserBean cnbean = new UserBean();
		try {
			conn = DataBaseOperate.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				registState = "用户名已存在" + userName2;
				return false;
			}
			// new RecDao().registNewLoveUser(getUserId(userName2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private String getUserId(String userName2) {
		String id = null;
		String sql = "select * from " + TABLENAME + " where " + USERNAME + "='"
				+ userName2 + "'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;

		UserBean cnbean = new UserBean();
		try {
			conn = DataBaseOperate.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				id = rs.getString(ID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getRegistSatte() {
		// TODO Auto-generated method stub
		return registState;
	}

}
