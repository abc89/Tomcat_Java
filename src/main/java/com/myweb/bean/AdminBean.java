package com.myweb.bean;

import java.sql.ResultSet;

/**
 * 系统管理人员 数据模型
 * @author e7691
 *
 */
public class AdminBean extends Bean{

	private int admin_ID ;
    private String admin_User_Name ;
    private String admin_Sex ;
    private String admin_Tel ;
	private String Admin_Dec;
	private String Admin_Name;
	public String getDec() {
		return Admin_Dec;
	}
	public int getID() {
		return admin_ID;
	}
	public void setID(int adminID) {
		admin_ID = adminID;
	}
	public String getUserName() {
		return admin_User_Name;
	}
	public void setUserName(String adminUsername) {
		admin_User_Name = adminUsername;
	}
//	private String getPassword() {
//		return admin_Password;
//	}
//	private void setPassword(String adminPassword) {
//		admin_Password = adminPassword;
//	}
	
	public String getSex() {
		return admin_Sex;
	}
	public void setSex(String adminSex) {
		admin_Sex = adminSex;
	}
	public String getTel() {
		return admin_Tel;
	}
	public void setTel(String adminTel) {
		admin_Tel = adminTel;
	}
	public String getJDBCMysqlSentence() {
		return admin_ID+admin_User_Name;
	}

	public String getName() {
		
		return Admin_Name;
	}
	public void setName(String name) {
		this.Admin_Name=name;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return Bean.VIPUSER;
	}

	public void setDec(String dec) {
		this.Admin_Dec=dec;
	}
	@Override
	public void configBean(ResultSet rs) {
		// TODO Auto-generated method stub
		
	}

  
    
	
}
