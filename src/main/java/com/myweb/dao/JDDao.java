package com.myweb.dao;

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
import com.myweb.dao.impl.ShopDaoImpl;
import com.myweb.db.DataBaseOperate;
import com.myweb.db.DataBaseOperateException;
public class JDDao implements ShopDaoImpl{
	  private String TABLENAME="item";
	     private String ID="id";
	     private String type="type";
	     private String price="price";
	     private String title="title";
	     private String imgUrl="imgUrl";
	     private String goodCount="goodCount";
	     private String itemID="itemID";
	 	public void add(JDBean bean) {
	 		if(bean==null){
	 			return;
	 		}
	 		String sql = "insert into "+TABLENAME+" (";
	 		sql += itemID+","+type+","+price+","+title+","+imgUrl+","+goodCount;
	 		sql += ") values(";
	 		sql += "'" + bean.getShopID() + "','" + bean.getShopType()
	 				+ "','" + bean.getPrice() + "','" + bean.getTitle() + "','"+ bean.getImg_url() + "','"+ bean.getGoodCount() + "'";
	 		sql += ")";
	 		Statement stat = null;
	 		ResultSet rs = null;
	 		Connection conn =null;
	 		try {
	 			System.out.println(sql);
	 			 conn = DataBaseOperate.getConnection();
	 			stat = conn.createStatement();
	 			stat.executeUpdate(sql);
	 		} catch (SQLException  e) {
	 			e.printStackTrace();
	 		}
	 		
	 	}

	 
	 	// ɾ��
	 	public void Delete(String where,String value) {
	 		String sql = "delete "+TABLENAME+" where ";
	 		sql +=where+"="+value;
	 		Statement stat = null;
	 		ResultSet rs = null;
	 		try {
	 			Connection conn = DataBaseOperate.getConnection();
	 			stat = conn.createStatement();
	 			stat.executeUpdate(sql);
	 		} catch (SQLException  e) {
	 			e.printStackTrace();
	 		}
	 	}


		public List<JDBean> getItemType(String search) {
			List<JDBean> beans=new ArrayList<JDBean>();
			String sql = "select * from "+TABLENAME+" where "+type+"='" + search + "'";
			Statement stat = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				Context initContext = new InitialContext();
				   DataSource ds  = (DataSource)initContext.lookup("java:/comp/env/jdbc/MySQLDS");
				//ds = (DataSource)envContext.lookup("jdbc/TestDB");
			conn = ds.getConnection();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			};
			try {
				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				while (rs.next()) {
					JDBean bean=this.builderBean(rs);
//				    boolean flag=false;
//					for (JDBean jdBean : beans) {
//						int p1=Integer.parseInt(jdBean.getPrice().substring(0,jdBean.getPrice().length()-3));
//						int p2=Integer.parseInt(bean.getPrice().substring(0,bean.getPrice().length()-3));
//						if(p2<p1){
//							beans.add(jdBean);
//							flag=true;
//						}
//					}
				//	if(!flag){
					beans.add(bean);
					//}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
	                   conn.close();
					if (stat != null)
						stat.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return beans;
		}

		protected JDBean builderBean(ResultSet rs) {
		
			JDBean bean=new JDBean();
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


		@Override
		public void insertShop(ShopBean bean) {
			// TODO Auto-generated method stub
			
		}



}
