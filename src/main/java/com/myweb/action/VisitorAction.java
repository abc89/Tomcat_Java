package com.myweb.action;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.dao.ArticleDao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.xml.XmlOperate;

public class VisitorAction extends ActionSupport {
	// private String type;
	// private String id;
	// private String arFilePath="./source/xml/test.xml";
	// public boolean update=true;//���Ƹ��� �Ƿ������¸��� ������������ݿ�
	// ��ȡ����������Ϣ
	// public String getType() {
	// return type;
	// }
	// public void setType(String type) {
	// this.type = type;
	// }
	//
	// //�����û����� execute����
	// public String execute() throws Exception {
	//
	// ArticleList articleList= new ArticleDao().getAllFirst();
	// updateArticleList(articleList);
	// return SUCCESS;
	//
	// }
	// /**
	// * ���¹��� ���� Ŀ¼�� �����б�
	// * @param articleList ��ݿ�����������Ϣ
	// */
	// private void updateArticleList(ArticleList articleList) {
	// if(arFilePath!=null){
	// File file=new File(arFilePath);
	// if(file.exists()){
	// int size=articleList.size();
	// for(int i=0;i<size;i++){
	// ArticleBean bean=articleList.getIndex(i);
	// XmlOperate.addArticleXml(Integer.toString(i),"name"+bean.getId(),bean.getContent());
	// // System.out.println(bean.getContent());
	//
	// }
	// XmlOperate.flush("./source/xml/test.xml");
	// }else{
	// throw new NullPointerException("Ŀ¼�� û�ж�Ӧ �����ļ�");
	// }
	// }
	// }

}
