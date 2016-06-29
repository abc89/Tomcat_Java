package com.myweb.action;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.struts2.ServletActionContext;

import com.myweb.dao.impl.AdminDao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.Login;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登陆处理
 * 
 * @author e7691
 * 
 */
public class GoLogin extends ActionSupport {
	private String msg;//登陆反馈，成功 or 失败
	private String type;//登陆类型
	private String userName;
	private String password;

	/***
	 * 注入： 映射方法 需符合格式
	 * 
	 */
	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String Password) {
		this.password = Password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String execute() throws Exception {
		if(Login.isLogin()){
			msg="已登陆";
			return INPUT;
		}
		System.out.println(userName + "  and " + password);
		DBDefine loginDf = new DBDefine(new DfineConfig()
				.configClientType(type).configUserName(userName)
				.configPassword(password));
		boolean flag = loginDf.checkLogin();
		if (flag) {
			loginDf.getBean();
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			session.setAttribute("user", loginDf.getBean());
			session.setMaxInactiveInterval(600); // ����Session�Ĺ���ʱ��Ϊ10����

			return SUCCESS;
		}
		msg = "登陆失败";
		return INPUT;
	}
}
