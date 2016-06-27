package com.myweb.action;

import com.opensymphony.xwork2.ActionSupport;

/***
 * 
 * 
 * @author e7691
 * 
 */
public class NavAction extends ActionSupport {
	private String type;
	private String game = "game";
	private String posts = "posts";

	/***
	 * 注入： 映射方法 需符合格式
	 * 
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    /***
     * action 执行方法
     */
	public String execute() throws Exception {
		System.out.println(type);
		if (type.compareTo("game") == 0) {
			return game;
		} else if (type.compareTo("posts") == 0) {
			return posts;
		} else {
			return INPUT;
		}

	}

}
