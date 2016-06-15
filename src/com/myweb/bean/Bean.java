package com.myweb.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 基本 用户 数据模型
 * @author e7691
 *
 */
public  abstract class Bean {
	//bean 子类类型
    public static final String COMMONUSER = "COM";
	 public static final String VIPUSER = "VIP";
	 public static final String ARTICLE = "ARTICLE";
	 public static final String GAME = "GAME";
	 //默认类型
	  public static final String DEFAULTTYPE = "DEFAULTTYPE";
	 /**
	  * 
	  * @return bean 子类类型
	  */
	public abstract String getType();
    public abstract void configBean(ResultSet rs)throws SQLException;
}
