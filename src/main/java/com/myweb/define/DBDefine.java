package com.myweb.define;

import com.myweb.bean.Bean;
import com.myweb.dao.AdminDao;
import com.myweb.dao.DefineDao;
import com.myweb.dao.UserDao;
/**
 * 数据库验证登陆
 * @author e7691
 *
 */
public class DBDefine implements LoginDefine {
	private DfineConfig config;
	private Bean bean;
	public DBDefine(DfineConfig config) {
		this.config=config;
	}

	@Override
	public boolean checkLogin() {
		boolean ok=false;
		DefineDao dao = null;
		System.out.println(config.getClientType());
		switch (config.getClientType()) {
		case "系统管理员":	dao=new UserDao();break;//.searchPasswordByUserName(config.getUserName(), config.getPassword()).getBean();	break;
		case "普通人员":   dao=new AdminDao();break;//l//.searchPasswordByUserName(config.getUserName(), config.getPassword()).getBean();break;
		}
		ok=dao==null?false:dao.hasUser(config.getUserName(), config.getPassword());
		bean=ok==true?dao.getBean():null;
	    return ok;
	}
    public Bean getBean(){
    	return bean;
    }

}
