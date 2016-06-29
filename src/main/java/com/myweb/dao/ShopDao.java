package com.myweb.dao;

import java.sql.ResultSet;

import com.myweb.bean.ShopBean;

/***
 * 商品推荐系统抽象父类
 * @author e7691
 *
 */
public abstract class ShopDao implements ShopDaoIntf {     
	protected abstract ShopBean builder(ResultSet rs);
}
