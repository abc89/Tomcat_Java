package com.myweb.action;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.bean.JDBean;
import com.myweb.bean.UserBean;
import com.myweb.dao.impl.ArticleDao;
import com.myweb.dao.impl.JDDao;
import com.myweb.define.DBDefine;
import com.myweb.define.DfineConfig;
import com.myweb.define.Login;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.xml.XmlOperate;

/**
 * 公共文章动作处理类 每日更新 从数据库获取 最新 文章数据 更新到 网站 文章内容目录 避免重复链接数据库
 * 
 * @author e7691
 * 
 */
public class ArticleAction extends ActionSupport {
	private String id;
	//article 
	private String title;//article title
	private String dec;//article description
	private String content;//article content
	
	//
	private String arFilePath = "./source/xml/test.xml";
	public boolean update = true;// 控制更新 是否有文章更新 有则重新向数据库 获取最新文章信息
	//action type
	private String type;
    private final String  allArticle="data";
    private final String  selectArticle="content";
    private final String  insertArticle="insert";
    private String returnAc;
    private String msg;
    /***
     * action 执行方法
     */
	public String execute() throws Exception {
	
            switch (type) {
			case allArticle: showAllArt();break;
			case selectArticle:showSelectArt();break;
			case insertArticle:insertNewArt();break;
			default:returnAc=INPUT;break;
			}
            return returnAc;
	}
    /**
     * 添加新文章
     */
	private void insertNewArt() {
		 if(!Login.isLogin()){
			 returnAc=INPUT;
			  return;
		  }
		boolean ok=title!=null&&content!=null&&dec!=null&&title.compareTo("")!=0&&content.compareTo("")!=0&&dec.compareTo("")!=0;
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserBean bean = (UserBean) session.getAttribute("user");
		String id = bean.getID();
		if(ok)new ArticleDao().insertArticle(title, dec, content,id);
		returnAc= "backWriteArticle";
		msg= ok?"添加新文章成功":"添加文章失败";
	}
    /***
     * 显示单篇文章所有内容
     */
	private void showSelectArt() {
		System.out.println("获取单篇文章");
		ArticleBean bean = new ArticleDao().getArticle(id);
		HttpSession session = ServletActionContext.getRequest()
				.getSession();
		session.setAttribute("articlebean", bean);
		returnAc = "articleContent";
		
	}
    /**
     * 所有文章标题 及 简介
     */
	private void showAllArt() {
		// TODO Auto-generated method stub
		System.out.println("获取所有文章描述");
		ArticleList articleList = new ArticleDao().getAllDec();
		Iterator<ArticleBean> iterators=articleList.iterator();
		for (ArticleBean articleBean : articleList) {
			
		}
		// updateArticleList(articleList);
		HttpSession session = ServletActionContext.getRequest()
				.getSession();
		session.setAttribute("articleList", articleList);
		returnAc=SUCCESS;
		
	}

	/**
	 * 更新公共 文章 目录下 文章列表
	 * 
	 * @param articleList
	 *            数据库最新文章信息
	 */
	private void updateArticleList(ArticleList articleList) {
		if (arFilePath != null) {
			File file = new File(arFilePath);
			if (file.exists()) {
				int size = articleList.size();
				for (int i = 0; i < size; i++) {
					ArticleBean bean = articleList.getIndex(i);
					XmlOperate.addArticleXml(Integer.toString(i),
							"name" + bean.getId(), bean.getContent());
					System.out.println(bean.getContent());

				}
				XmlOperate.flush("./source/xml/test.xml");
			} else {
				throw new NullPointerException("目录下 没有对应 文章文件");
			}
		}
	}
	/***
	 * 以下为 注入 映射方法 需符合格式
	 * 
	 */
	  public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getTitle() {
	    	return title;
	    }
	    
	    public void setTitle(String title) {
	    	this.title = title;
	    }
	    
	    public String getDec() {
	    	return dec;
	    }
	    
	    public void setDec(String dec) {
	    	this.dec = dec;
	    }
	    
	    public String getContent() {
	    	return content;
	    }
	    
	    public void setContent(String content) {
	    	this.content = content;
	    }
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

}
