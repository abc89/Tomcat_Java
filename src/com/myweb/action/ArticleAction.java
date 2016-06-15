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
 * �������¶���������  ÿ�ո��� �����ݿ��ȡ ���� �������� ���µ� ��վ ��������Ŀ¼
 * �����ظ��������ݿ�
 * @author e7691
 *
 */
public class ArticleAction extends ActionSupport {
	private String id;
	private String arFilePath="./source/xml/test.xml";
    public boolean update=true;//���Ƹ��� �Ƿ������¸��� �������������ݿ� ��ȡ����������Ϣ
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
				System.out.println("��ȡ������������");
			    ArticleList articleList=  new ArticleDao().getAllDec();
			//	updateArticleList(articleList);
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("articleList",articleList);
				return SUCCESS;
		    }
			else if(type.compareTo("content")==0){
				System.out.println("��ȡ��ƪ����");
			    ArticleBean bean=  new ArticleDao().getArticle(id);				
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("articlebean",bean);
				returnAc="articleContent";
				return returnAc;
		    }
			
			//��½����
			return INPUT;
	}

	/**
	 * ���¹��� ���� Ŀ¼�� �����б�
	 * @param articleList ���ݿ�����������Ϣ
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
	    		  throw new NullPointerException("Ŀ¼�� û�ж�Ӧ �����ļ�");
	    	  }
	     }
	}

	
}

