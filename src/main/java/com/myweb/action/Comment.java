package com.myweb.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.bean.CommentBean;
import com.myweb.dao.CommentDao;
import com.opensymphony.xwork2.ActionSupport;

/**
 * comment.action 动作处理
 * 
 * @author e7691
 * 
 */
public class Comment extends ActionSupport {
	private String msg;
	private String fenshu;

	public String getUsername() {
		return fenshu;
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
	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	// 处理用户请求 execute方法
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String username = session.getAttribute("userName").toString();
		String userId = session.getAttribute("sessionId").toString();
		if (username == null || userId == null) {
			return INPUT;
		}
		new CommentDao().updateComment(new CommentBean(username, fenshu, msg));
		return SUCCESS;
	}

}
