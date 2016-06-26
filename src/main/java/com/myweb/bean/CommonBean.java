package com.myweb.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * 普通人员 数据模型
 * 
 * @author e7691
 * 
 */
public class CommonBean extends Bean {
	private int common_ID;
	private String common_User_Name;
	// private String common_Password ;
	private String common_name;
	private String common_Sex;
	private String common_Tel;
	private String common_dec;

	public int getID() {
		return common_ID;
	}

	public void setID(int commonID) {
		common_ID = commonID;
	}

	public String getUserName() {
		return common_User_Name;
	}

	public void setUserName(String commonUserName) {
		common_User_Name = commonUserName;
	}

	// public String getPassword() {
	// return common_Password;
	// }

	public String getSex() {
		return common_Sex;
	}

	public String getTel() {
		return common_Tel;
	}

	// @Override
	// public void setPassword(String password) {
	// common_Password=password;
	//
	// }
	public void setSex(String sex1) {
		common_Sex = sex1;

	}

	public void setTel(String tel1) {
		common_Tel = tel1;

	}

	public String getType() {
		// TODO Auto-generated method stub
		return Bean.COMMONUSER;
	}

	public String getDec() {
		// TODO Auto-generated method stub
		return common_dec;
	}

	public void setDec(String dec) {
		this.common_dec = dec;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return common_name;
	}

	public void setName(String name) {
		this.common_name = name;
	}

	@Override
	public void configBean(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

	}

}
