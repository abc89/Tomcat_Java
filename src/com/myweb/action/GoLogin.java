package com.myweb.action;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.struts2.ServletActionContext;

import com.myweb.dao.AdminDao;
import com.myweb.dao.CommonDao;
import com.myweb.dao.Dao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 登陆动作 处理
 * @author e7691
 *
 */
public class GoLogin extends ActionSupport {
	private String msg;
    private String type;
    private String userName;
    private String password;
    public String getUsername() {
		return userName;
	}
    /***
     * setUserName   Name 大写N 错误
     */
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
    /***
     * setUsername   Name 小写n 正确
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
	//处理用户请求 execute方法
	public String execute() throws Exception {	
		System.out.println(userName+"  and "+password);
		DBDefine loginDf=new DBDefine(new DfineConfig().configClientType(type).configUserName(userName).configPassword(password));
		boolean flag=loginDf.checkLogin();
	//	System.out.println(userName+"  and "+password);
			if(flag){
			         loginDf.getBean();
					System.out.println("登陆成功");
					HttpSession session = ServletActionContext.getRequest().getSession();
					session.setAttribute("user",loginDf.getBean());
					session.setMaxInactiveInterval(600);		//设置Session的过期时间为10分钟
					
				return SUCCESS;
				}
			//strcut 机制 自动添加到 会话，通过request.getArri...获得 动态改变内容
			msg="登陆失败，密码或用户名错误";
			//登陆错误
			return INPUT;
	}
}
