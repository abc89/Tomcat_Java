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
public interface ShopDaoInfo {
	/***
	 * 添加 单个ShopBean
	 * @param bean
	 */
	void insert(ShopBean bean);
	/**
	 * 添加 多个 ShopBean 批处理
	 * @param beans List<ShopBean>
	 */
	void insert(List<ShopBean> beans);
	/***
	 * 根据查找内容
	 * 返回 shopbean
	 * @param search c查找内容
	 */
	ShopBean get(String search);
}
