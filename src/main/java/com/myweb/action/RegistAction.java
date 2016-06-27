package com.myweb.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.dao.UserDao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.Login;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册 基本信息注册
 * 注册验证方式
 * @author e7691
 *
 */
public class RegistAction extends ActionSupport {
	private String msg;
	private String type;
	private String userName;
	private String password;

	public String getUsername() {
		return userName;
	}

	/***
	 * 注入： 映射方法 需符合格式
	 * 
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

	/***
     * action 执行方法
     */
	public String execute() throws Exception {
		 //判断是否登陆     Login 用户登录管理
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