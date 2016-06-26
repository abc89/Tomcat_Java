package com.myweb.dao.impl;
/**
 * 数据库提取数据  验证登陆，注册
 * @author e7691
 *
 */
public interface DefineDaoImpl {

	boolean hasUser(String userName,String password);
}
