package com.myweb.bean;

import java.sql.ResultSet;

/**
 * 系统管理人员 数据模型
 * @author e7691
 *
 */
public class UserBean extends Bean{

	private String admin_ID ;
    private String admin_User_Name ;

	public String getID() {
		return admin_ID;
	}
	public void setID(String adminID) {
		admin_ID = adminID;
	}
	public String getUserName() {
		return admin_User_Name;
	}
	public void setUserName(String adminUsername) {
		admin_User_Name = adminUsername;
	}

	public String getJDBCMysqlSentence() {
		return admin_ID+admin_User_Name;
	}


	public String getType() {
		// TODO Auto-generated method stub
		return Bean.VIPUSER;
	}

	@Override
	public void configBean(ResultSet rs) {
		// TODO Auto-generated method stub
		
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

  
    
	
}
