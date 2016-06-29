package com.myweb.define;

import com.myweb.bean.Bean;
import com.myweb.dao.impl.AdminDao;
import com.myweb.dao.impl.DefineDao;
import com.myweb.dao.impl.UserDao;

/**
 * 数据库验证登陆
 * 
 * @author e7691
 * 
 */
public class DBDefine implements LoginDefine {
	private DfineConfig config;
	private Bean bean;
    
	public DBDefine(DfineConfig config) {
		this.config = config;
	}

	/***
	 * 验证登陆
	 * @return true: logining success
	 *          false: logining fail
	 */
	@Override
	public boolean checkLogin() {
		boolean ok = false;
		DefineDao dao = null;
		System.out.println(config.getClientType());
		switch (config.getClientType()) {
		case "系统管理员":
			dao = new UserDao();
			break;// .searchPasswordByUserName(config.getUserName(),
					// config.getPassword()).getBean(); break;
		case "普通人员":
			dao = new AdminDao();
			break;// l//.searchPasswordByUserName(config.getUserName(),
					// config.getPassword()).getBean();break;
		}
		ok = dao == null ? false : dao.hasUser(config.getUserName(),
				config.getPassword());
		bean = ok == true ? dao.getBean() : null;
		return ok;
	}
    /***
     * 验证通过 可 获取 个人基本信息
     * @return
     */
	public Bean getBean() {
		return bean;
	}

}
