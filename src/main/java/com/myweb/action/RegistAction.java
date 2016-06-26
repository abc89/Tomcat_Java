package com.myweb.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.dao.UserDao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.Login;
import com.opensymphony.xwork2.ActionSupport;

public class RegistAction extends ActionSupport {
	private String msg;
	private String type;
	private String userName;
	private String password;

	public String getUsername() {
		return userName;
	}

	/***
	 * setUserName Name 大写N 错误
	 */
	// public void setUserName(String userName) {
	// this.userName = userName;
	// }
	/***
	 * setUsername Name 小写n 正确
	 */
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

	// 处理用户请求 execute方法
	public String execute() throws Exception {
		if (!Login.isLogin()) {
			UserDao dao = new UserDao();
			boolean ok = dao.regist(userName, password);
			if (!ok) {
				msg = dao.getRegistSatte();
				return INPUT;
			}
			return SUCCESS;
		}
		return INPUT;
	}
}