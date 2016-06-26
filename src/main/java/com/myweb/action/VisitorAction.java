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
//    private String type;
//	private String id;
//	private String arFilePath="./source/xml/test.xml";
//    public boolean update=true;//控制更新 是否有文章更新 有则重新向数据库 获取最新文章信息
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	
//	//处理用户请求 execute方法
//	public String execute() throws Exception {	
//
//		    ArticleList articleList=  new ArticleDao().getAllFirst();
//			updateArticleList(articleList);
//			return SUCCESS;
//
//	}
//	/**
//	 * 更新公共 文章 目录下 文章列表
//	 * @param articleList 数据库最新文章信息
//	 */
//	private void updateArticleList(ArticleList articleList) {
//	     if(arFilePath!=null){
//	    	  File file=new File(arFilePath);
//	    	  if(file.exists()){
//	    		  int size=articleList.size();
//	    		  for(int i=0;i<size;i++){
//	    			  ArticleBean bean=articleList.getIndex(i);
//	    			  XmlOperate.addArticleXml(Integer.toString(i),"name"+bean.getId(),bean.getContent());
//	    			//  System.out.println(bean.getContent());
//	    		      
//	    		  }
//	    		  XmlOperate.flush("./source/xml/test.xml");
//	    	  }else{
//	    		  throw new NullPointerException("目录下 没有对应 文章文件");
//	    	  }
//	     }
//	}

	
}


