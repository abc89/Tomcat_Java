package com.myweb.define;

import com.myweb.bean.Bean;
import com.myweb.dao.AdminDao;
import com.myweb.dao.CommonDao;
import com.myweb.dao.Dao;

public class DBDefine implements LoginDefine {
	private DfineConfig config;
	private Bean bean;
	public DBDefine(DfineConfig config) {
		this.config=config;
	}

	@Override
	public boolean checkLogin() {
		boolean ok=false;
		Dao dao = null;
		System.out.println(config.getClientType());
		switch (config.getClientType()) {
		case "ϵͳ����Ա":	dao=new AdminDao();break;//.searchPasswordByUserName(config.getUserName(), config.getPassword()).getBean();	break;
		case "��ͨ��Ա":   dao=new CommonDao();break;//l//.searchPasswordByUserName(config.getUserName(), config.getPassword()).getBean();break;
		}
		ok=dao==null?false:dao.hasOne(config.getUserName(), config.getPassword());
		bean=ok==true?dao.getBean():null;
	    return ok;
	}
    public Bean getBean(){
    	return bean;
    }

}
