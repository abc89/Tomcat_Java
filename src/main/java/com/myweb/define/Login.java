package com.myweb.define;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.bean.Bean;

public class Login {
	public static boolean isLogin() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Bean bean = (Bean) session.getAttribute("user");
		if (bean == null || bean.getType() == null) {
			return false;
		} else {
			return true;
		}
	}
}
