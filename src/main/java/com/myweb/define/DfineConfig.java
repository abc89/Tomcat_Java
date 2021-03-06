package com.myweb.define;

/**
 * 验证信息 配置类
 * @author e7691
 *
 */
public class DfineConfig {
	private String userName;
	private String password;
	private String clientType;
	private String yzContent;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getClientType() {
		return clientType;
	}

	public String getYzContent() {
		return yzContent;
	}


	public DfineConfig() {
	}

	public DfineConfig configUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public DfineConfig configPassword(String password) {
		this.password = password;
		return this;
	}

	public DfineConfig configClientType(String clientType) {
		this.clientType = clientType;
		return this;
	}

	public DfineConfig configYzContent(String yzContent) {
		this.yzContent = yzContent;
		return this;
	}
}
