package com.myweb.dao;

import java.sql.ResultSet;
import java.util.List;

import com.myweb.bean.ShopBean;

/**
 * 商品数据存储接口
 * 
 * @author e7691
 * 
 */
public interface ShopDaoIntf {
	/***
	 * 添加 ShopBean
	 * @param bean
	 */
	void insert(ShopBean bean);
	/***
	 * 根据查找内容
	 * 返回 shopbean
	 * @param search c查找内容
	 */
	ShopBean get(String search);
}
