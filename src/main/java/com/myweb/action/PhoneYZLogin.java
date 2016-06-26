package com.myweb.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.YZDefine;
import com.opensymphony.xwork2.ActionSupport;

public class PhoneYZLogin extends ActionSupport {
	private String phoneYZ;
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
	public void setType(String phoneYZ) {
		this.type = type;
	}
	public String getPhoneYZ() {
		return phoneYZ;
	}
	public void setPhoneYZ(String phoneYZ) {
		this.phoneYZ = phoneYZ;
	}
	//处理用户请求 execute方法
	public String execute() throws Exception {	
		YZDefine loginDf=new YZDefine(new DfineConfig().configClientType(type).configUserName(userName).configPassword(password).configYzContent(phoneYZ));
		boolean flag=loginDf.checkLogin();
			if(flag){
			
					System.out.println("登陆成功");
					HttpSession session = ServletActionContext.getRequest().getSession();
					session.setAttribute("userName","abc");
					session.setAttribute("sessionId", "123123");
					session.setMaxInactiveInterval(600);		//设置Session的过期时间为10分钟
				return SUCCESS;
				}
			
			//登陆错误
			return INPUT;
	}
}
