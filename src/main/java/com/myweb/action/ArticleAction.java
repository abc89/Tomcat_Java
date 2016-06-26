package com.myweb.action;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;



import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.dao.ArticleDao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.Login;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.xml.XmlOperate;
/**
 * 公共文章动作处理类  每日更新 从数据库获取 最新 文章数据 更新到 网站 文章内容目录
 * 避免重复链接数据库
 * @author e7691
 *
 */
public class ArticleAction extends ActionSupport {
	private String id;
	private String arFilePath="./source/xml/test.xml";
    public boolean update=true;//控制更新 是否有文章更新 有则重新向数据库 获取最新文章信息
    private String type;
    private String returnAc;
    public String getId() {
		return id;
	}

	public void setType(String t) {
		this.type = t;
	}
	  public String getType() {
			return type;
		}

		public void setId(String Id) {
			this.id = Id;
		}
	public String execute() throws Exception {	
		
		    
			if(type.compareTo("data")==0){
				System.out.println("获取所有文章描述");
			    ArticleList articleList=  new ArticleDao().getAllDec();
			//	updateArticleList(articleList);
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("articleList",articleList);
				return SUCCESS;
		    }
			else if(type.compareTo("content")==0){
				System.out.println("获取单篇文章");
			    ArticleBean bean=  new ArticleDao().getArticle(id);				
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("articlebean",bean);
				returnAc="articleContent";
				return returnAc;
		    }
			
			//登陆错误
			return INPUT;
	}

	/**
	 * 更新公共 文章 目录下 文章列表
	 * @param articleList 数据库最新文章信息
	 */
	private void updateArticleList(ArticleList articleList) {
	     if(arFilePath!=null){
	    	  File file=new File(arFilePath);
	    	  if(file.exists()){
	    		  int size=articleList.size();
	    		  for(int i=0;i<size;i++){
	    			  ArticleBean bean=articleList.getIndex(i);
	    			  XmlOperate.addArticleXml(Integer.toString(i),"name"+bean.getId(),bean.getContent());
	    			  System.out.println(bean.getContent());
	    		      
	    		  }
	    		  XmlOperate.flush("./source/xml/test.xml");
	    	  }else{
	    		  throw new NullPointerException("目录下 没有对应 文章文件");
	    	  }
	     }
	}

	
}

