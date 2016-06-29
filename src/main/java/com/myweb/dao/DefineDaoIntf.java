package com.myweb.dao;

/**
 * 数据库提取数据 验证登陆，注册
 * 
 * @author e7691
 * 
 */
public interface DefineDaoIntf {

	boolean hasUser(String userName, String password);
}
