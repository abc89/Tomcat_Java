package com.myweb.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.Login;
import com.opensymphony.xwork2.ActionSupport;
/***
 * self article
 * @author e7691
 *
 */
public class WriteArticleAc extends ActionSupport {
	
	//处理用户请求 execute方法
	public String execute() throws Exception {	
			if(Login.isLogin()){
			        
				return SUCCESS;
				}
		
			return INPUT;
	}
}