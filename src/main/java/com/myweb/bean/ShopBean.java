package com.myweb.bean;

import java.util.List;

/**
 * 商品数据分类存储
 * 
 * @author e7691
 * 
 */
public abstract class ShopBean {

	public abstract String getShopType();
	public abstract void configComment(List<String> list);
}
