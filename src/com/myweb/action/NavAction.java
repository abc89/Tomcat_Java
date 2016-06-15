package com.myweb.action;

import com.opensymphony.xwork2.ActionSupport;

/***
 * µ¼º½À¸
 * @author e7691
 *
 */
public class NavAction extends ActionSupport {
	private String type;
	private String game="game";
	private String posts="posts";
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String execute() throws Exception {
		System.out.println(type);
		if(type.compareTo("game")==0){
			return game;
		}
		else if(type.compareTo("posts")==0){			
			return posts;
		}else{
			return INPUT;
		}
		
	}

}
