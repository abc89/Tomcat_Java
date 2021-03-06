package com.myweb.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.YZDefine;
import com.opensymphony.xwork2.ActionSupport;
/***
 * 验证方式
 * @author e7691
 *
 */
public class PhoneYZLogin extends ActionSupport {
	private String phoneYZ;
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

	public void setType(String phoneYZ) {
		this.type = type;
	}

	public String getPhoneYZ() {
		return phoneYZ;
	}

	public void setPhoneYZ(String phoneYZ) {
		this.phoneYZ = phoneYZ;
	}

	/***
     * action 执行方法
     */
	public String execute() throws Exception {
		YZDefine loginDf = new YZDefine(new DfineConfig()
				.configClientType(type).configUserName(userName)
				.configPassword(password).configYzContent(phoneYZ));
		boolean flag = loginDf.checkLogin();
		if (flag) {

			System.out.println("��½�ɹ�");
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			session.setAttribute("userName", "abc");
			session.setAttribute("sessionId", "123123");
			session.setMaxInactiveInterval(600); // ����Session�Ĺ���ʱ��Ϊ10����
			return SUCCESS;
		}

		// ��½����
		return INPUT;
	}
}
