package com.myweb.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.Login;
import com.opensymphony.xwork2.ActionSupport;

/***
 * ���˼�¼ ������Ӧ
 * 
 * @author e7691
 * 
 */
public class WriteArticleAc extends ActionSupport {

	// �����û����� execute����
	public String execute() throws Exception {
		if (Login.isLogin()) {

			return SUCCESS;
		}

		return INPUT;
	}
}