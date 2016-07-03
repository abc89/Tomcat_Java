package com.myweb.dao.impl;

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
import com.myweb.bean.JDBean;
import com.myweb.bean.ShopBean;
import com.myweb.bean.UserBean;
import com.myweb.db.impl.DataBaseOperate;
import com.myweb.db.impl.DataBaseOperateException;
/***
 * 推荐结果存 取
 * @author e7691
 *
 */
public class RecDao {
	private String TABLENAME = "lovetable";
	private String userID = "userID";
	private String love = "love";
	private String splite = "splite";
    private DataBaseOperate baseOperate=DataBaseOperate.getInstance();
	// 删除
	public void Delete(String where, String value) {
		String sql = "delete " + TABLENAME + " where ";
		sql += where + "=" + value;
		DataBaseOperate.getInstance().executeSingle(sql);
	}

	public List<ShopBean> getRecBeans(String id) {
		List<ShopBean> beans = new ArrayList<ShopBean>();
		String sql = "select * from " + TABLENAME + " where " + userID + "='"
				+ id + "'";
		System.out.println("查询推荐" + sql);
		try {
			ResultSet rs=baseOperate.select(sql);
			if (rs.next()) {
				System.out.println(rs.getString(love));
				beans = getLoveBeans(rs.getString(love));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return beans;
	}

	private List<ShopBean> getLoveBeans(String loves) {
		List<ShopBean> beans = new ArrayList<ShopBean>();
		if (loves != null && loves.indexOf(splite) != -1) {
			String lv = loves.substring(0, loves.length() - splite.length());
			System.out.println(lv);
			if (lv.indexOf(splite) != -1) {
				String[] ls = lv.split(splite);
				for (String string : ls) {
					JDDao dao=new JDDao();
					List<ShopBean> bs =dao.getItemType(string);
					if (bs.size() > 2) {
						beans.add(bs.get(0));
						beans.add(bs.get(1));
					} else {
						beans.addAll(bs);
					}
				}
			} else {
				List<ShopBean> bs = new JDDao().getItemType(lv);
				if (bs.size() > 2) {
					beans.add(bs.get(0));
					beans.add(bs.get(1));
				} else {
					beans.addAll(bs);
				}
			}
		}
		System.out.println("获得推荐数目吗" + beans.size());
		return beans;
	}

	public void setLove(String id, String search) {
		String nlove = getLove(id);
		String newLove = null;
		if (nlove == null) {
			newLove = search + splite;
		} else {
			newLove = nlove + search + splite;
		}
		String sql = "update " + TABLENAME + " set ";
		sql += love + "='" + newLove + "'";
		// sql += PASSWORD+"='" + cnbean.getPassword() + "',";
		sql += " where " + userID + "='" + id + "'";
		System.out.println(sql);
		baseOperate.executeSingle(sql);
		
	}

	private String getLove(String id) {
		String nlove = null;
		String sql = "select * from " + TABLENAME + " where " + userID + "='"
				+ id + "'";
		System.out.println("查询推荐" + sql);
		try {
			ResultSet rs = baseOperate.select(sql);
			if (rs.next()) {
				System.out.println(rs.getString(this.love));
				nlove = rs.getString(love);
			} else {
				registNewLoveUser(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return nlove;
	}

	public void registNewLoveUser(String uID) {
		System.out.println("usus" + uID);
		if (uID != null) {
			String sql = "insert into " + TABLENAME + " (";
			sql += userID;
			sql += ") values(";
			sql += "'" + uID + "'";
			sql += ")";
			System.out.println(sql);
			 baseOperate.executeSingle(sql);
		}
	}

}
