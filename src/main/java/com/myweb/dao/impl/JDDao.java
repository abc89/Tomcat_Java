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
import com.myweb.dao.ShopDaoInfo;
import com.myweb.db.impl.DataBaseOperate;
import com.myweb.db.impl.DataBaseOperateException;
import com.myweb.exception.MethodNoImplementException;
/***
 * 京东数据存取
 * @author e7691
 *
 */
public class JDDao implements ShopDaoInfo {
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
		baseOperate.executeSingle(sql);

	}
    /**
     * get items like search
     * @param search
     * @return
     */
	public List<ShopBean> getItemType(String search) {
		List<ShopBean> beans = new ArrayList<ShopBean>();
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
	public void insert(List<ShopBean> jDBeans) {
		List<String> sqls=new ArrayList<String>();
		for (ShopBean shopBean : jDBeans) {		
			JDBean bean=(JDBean)shopBean;
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
			sqls.add(sql);
		}
		//批处理语句不为空
		if(!sqls.isEmpty()){			
			Boolean exResult=baseOperate.excuteBatch(sqls);
		}
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
	@Override
	public void insert(ShopBean bean) {
		try {
			throw new MethodNoImplementException();
		} catch (MethodNoImplementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
