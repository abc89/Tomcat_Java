package com.myweb.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���� �û� ����ģ��
 * @author e7691
 *
 */
public  abstract class Bean {
	//bean ��������
    public static final String COMMONUSER = "COM";
	 public static final String VIPUSER = "VIP";
	 public static final String ARTICLE = "ARTICLE";
	 public static final String GAME = "GAME";
	 //Ĭ������
	  public static final String DEFAULTTYPE = "DEFAULTTYPE";
	 /**
	  * 
	  * @return bean ��������
	  */
	public abstract String getType();
    public abstract void configBean(ResultSet rs)throws SQLException;
}
