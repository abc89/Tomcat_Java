package com.myweb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myweb.bean.JDBean;
import com.myweb.bean.ShopBean;
import com.myweb.bean.ShopCommentBean;
import com.myweb.dao.ShopComDaoIntf;
import com.myweb.dao.ShopDao;
import com.myweb.dao.ShopDaoIntf;
import com.myweb.db.impl.DataBaseOperate;
/***
 * 物品评论获取
 * @author e7691
 *
 */
public class JDCommentDao  extends ShopDao implements ShopComDaoIntf {
	private String TABLENAME = "jd_goods_comment";
	private String CONTENT = "comment";
	private String SHOPID = "shopid";
	
	private DataBaseOperate baseOperate=DataBaseOperate.getInstance();

	@Override
	public void insert(ShopBean sbean) {
		ShopCommentBean bean=(ShopCommentBean)sbean;
		if (bean == null) {
			return;
		}
		String sql = "insert into " + TABLENAME + " (";
		sql += CONTENT + "," + SHOPID ;
		sql += ") values(";
		sql += "'" + bean.getContent() + "','" + bean.getShopId() + "'";
		sql += ")";
		System.out.println(sql);
		baseOperate.execute(sql);
	}

	@Override
	public ShopBean get(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopCommentBean> getComments(String id) {
		List<ShopCommentBean> beans = new ArrayList<ShopCommentBean>();
		String sql = "select * from " + TABLENAME + " where " + SHOPID + "='"
				+ id + "'";
		
		try {
			ResultSet rs = baseOperate.select(sql);
			while (rs.next()) {
				ShopCommentBean bean = (ShopCommentBean)this.builder(rs);		
			    beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return beans;
	}

	@Override
	protected ShopBean builder(ResultSet rs) {

		ShopCommentBean bean = new ShopCommentBean();
		try {
			bean.setContent(rs.getString(CONTENT));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}



	

}
