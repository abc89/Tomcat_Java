package com.myweb.dao.impl;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.bean.Bean;
import com.myweb.bean.JDBean;
import com.myweb.bean.ShopBean;
import com.myweb.dao.ShopDaoIntf;
import com.myweb.db.impl.DataBaseOperate;
import com.myweb.db.impl.DataBaseOperateException;
/***
 * 京东数据存取
 * @author e7691
 *
 */
public class JDDao implements ShopDaoIntf {
	private String TABLENAME = "item";
	private String ID = "id";
	private String type = "type";
	private String price = "price";
	private String title = "title";
	private String imgUrl = "imgUrl";
	private String goodCount = "goodCount";
	private String itemID = "itemID";
	private  DataBaseOperate baseOperate=DataBaseOperate.getInstance();
    /**
     * delete item by key
     * @param where table key
     * @param value key Value
     */
	public void Delete(String where, String value) {
		String sql = "delete " + TABLENAME + " where ";
		sql += where + "=" + value;
		baseOperate.execute(sql);

	}
    /**
     * get items like search
     * @param search
     * @return
     */
	public List<JDBean> getItemType(String search) {
		List<JDBean> beans = new ArrayList<JDBean>();
		String sql = "select * from " + TABLENAME + " where " + type + "='"
				+ search + "'";
		
		try {
			ResultSet rs = baseOperate.select(sql);
			while (rs.next()) {
				JDBean bean = this.builderBean(rs);
				// boolean flag=false;
				// for (JDBean jdBean : beans) {
				// int
				// p1=Integer.parseInt(jdBean.getPrice().substring(0,jdBean.getPrice().length()-3));
				// int
				// p2=Integer.parseInt(bean.getPrice().substring(0,bean.getPrice().length()-3));
				// if(p2<p1){
				// beans.add(jdBean);
				// flag=true;
				// }
				// }
				// if(!flag){
				beans.add(bean);
				// }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return beans;
	}

	protected JDBean builderBean(ResultSet rs) {

		JDBean bean = new JDBean();
		try {
			bean.setShopID(rs.getString(itemID));
			bean.setImg_url(rs.getString(imgUrl));
			bean.setGoodCount(rs.getString(goodCount));
			bean.setPrice(rs.getString(price));
			bean.setTitle(rs.getString(title));
			bean.setShopType(rs.getString(type));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	/***
	 * insert  new item in itemtable 
	 */
	@Override
	public void insert(ShopBean sbean) {
		JDBean bean=(JDBean)sbean;
		if (bean == null) {
			return;
		}
		String sql = "insert into " + TABLENAME + " (";
		sql += itemID + "," + type + "," + price + "," + title + "," + imgUrl
				+ "," + goodCount;
		sql += ") values(";
		sql += "'" + bean.getShopID() + "','" + bean.getShopType() + "','"
				+ bean.getPrice() + "','" + bean.getTitle() + "','"
				+ bean.getImg_url() + "','" + bean.getGoodCount() + "'";
		sql += ")";
		baseOperate.execute(sql);
	}
	@Override
	public ShopBean get(String search) {
		JDBean bean = new JDBean();
		String sql = "select * from " + TABLENAME + " where " + itemID + "='"
				+ search + "'";
		
		try {
			ResultSet rs = baseOperate.select(sql);
			if (rs.next()) {
				 bean = this.builderBean(rs);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return bean;
	}

}
