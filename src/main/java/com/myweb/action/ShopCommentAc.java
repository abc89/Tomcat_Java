package com.myweb.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.bean.CommentBean;
import com.myweb.bean.JDBean;
import com.myweb.bean.ShopBean;
import com.myweb.bean.ShopCommentBean;
import com.myweb.bean.UserBean;
import com.myweb.bean.factory.JDFactory;
import com.myweb.dao.ShopComDaoIntf;
import com.myweb.dao.ShopDaoIntf;
import com.myweb.dao.impl.JDCommentDao;
import com.myweb.dao.impl.JDDao;
import com.myweb.dao.impl.RecDao;
import com.myweb.define.Login;
import com.net.crawler.JDCrawler;
import com.opensymphony.xwork2.ActionSupport;
import com.xml.XmlOperate;

public class ShopCommentAc extends ActionSupport {
	private String id;
	private String returnAc;
	private ShopComDaoIntf shopDaoImpl=new JDCommentDao();
	/***
	 * 注入： 映射方法 需符合格式
	 * 
	 */
	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}
	/***
     * action 执行方法
     */
	public String execute() throws Exception {

		if (Login.isLogin()&&id!=null) {

			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			ShopBean sbean=new JDDao().get(id);
			List<ShopCommentBean> beans =shopDaoImpl.getComments(id);
			if (beans.isEmpty()) {
				
				ShopCommentBean bean=new ShopCommentBean();
				JDFactory.getInstance().configShopCommentBean(JDCrawler.creatTBCrawler().searchComment(id, "1"), bean);
				ShopComDaoIntf comDaoImpl=new JDCommentDao();
				bean.setShopId(id);
				System.out.println(bean.getContent()+" "+bean.getShopId());
			    comDaoImpl.insert(bean);
				bean.setContent("评论为空");
                beans.add(bean);
			}
			// 存储用户查找记录
			session.setAttribute("shopbean", sbean);
			session.setAttribute("commentbeans", beans);
			return SUCCESS;
		}
		// 登陆错误
		return INPUT;
	}
    

}
