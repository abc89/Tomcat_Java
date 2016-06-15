package com.myweb.define;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.dao.AdminDao;
import com.myweb.dao.CommonDao;

public class YZDefine implements LoginDefine {
	private DfineConfig config;
	public YZDefine(DfineConfig config) {
		this.config=config;
	}
	public boolean checkLogin() {
		if(!checkYZ(config.getYzContent())){
			return false;
		}
		boolean ok=new DBDefine(config).checkLogin();		
	    return ok;
	}
	private boolean checkYZ(String yzContent) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String ys=session.getAttribute("YZContent").toString();
		if(yzContent.compareTo(ys)==0){
			return true;
		}
		return false;
	}
}
