package com.myweb.action;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.struts2.ServletActionContext;

import com.myweb.dao.AdminDao;
import com.myweb.dao.CommonDao;
import com.myweb.dao.Dao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.opensymphony.xwork2.ActionSupport;


/**
 * ��½���� ����
 * @author e7691
 *
 */
public class GoLogin extends ActionSupport {
	private String msg;
    private String type;
    private String userName;
    private String password;
    public String getUsername() {
		return userName;
	}
    /***
     * setUserName   Name ��дN ����
     */
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
    /***
     * setUsername   Name Сдn ��ȷ
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
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	//�����û����� execute����
	public String execute() throws Exception {	
		System.out.println(userName+"  and "+password);
		DBDefine loginDf=new DBDefine(new DfineConfig().configClientType(type).configUserName(userName).configPassword(password));
		boolean flag=loginDf.checkLogin();
	//	System.out.println(userName+"  and "+password);
			if(flag){
			         loginDf.getBean();
					System.out.println("��½�ɹ�");
					HttpSession session = ServletActionContext.getRequest().getSession();
					session.setAttribute("user",loginDf.getBean());
					session.setMaxInactiveInterval(600);		//����Session�Ĺ���ʱ��Ϊ10����
					
				return SUCCESS;
				}
			//strcut ���� �Զ���ӵ� �Ự��ͨ��request.getArri...��� ��̬�ı�����
			msg="��½ʧ�ܣ�������û�������";
			//��½����
			return INPUT;
	}
}
