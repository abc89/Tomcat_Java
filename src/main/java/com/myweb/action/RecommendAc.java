package com.myweb.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.myweb.bean.ArticleBean;
import com.myweb.bean.ArticleList;
import com.myweb.bean.Bean;
import com.myweb.bean.JDBean;
import com.myweb.bean.ShopBean;
import com.myweb.bean.UserBean;
import com.myweb.bean.factory.JDFactory;
import com.myweb.dao.JDDao;
import com.myweb.dao.RecDao;
import com.myweb.define.Login;
import com.net.crawler.JDCrawler;
import com.opensymphony.xwork2.ActionSupport;
import com.xml.XmlOperate;

/**
 * 推荐物品
 * 
 * @author e7691
 * 
 */
public class RecommendAc extends ActionSupport {
	private String id;
	private String arFilePath = "./source/xml/test.xml";
	public boolean update = true;// 控制更新 是否有文章更新 有则重新向数据库 获取最新文章信息
	private String search;
	private String returnAc;

	public String getId() {
		return id;
	}

	public void setSearch(String t) {
		this.search = t;
	}

	public String getSearch() {
		return search;
	}

	public void setId(String Id) {
		this.id = Id;
	}

	public String execute() throws Exception {

		if (Login.isLogin()) {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			UserBean bean = (UserBean) session.getAttribute("user");
			String id = bean.getID();
			System.out.println("用户id" + id);
			List<JDBean> beans = new RecDao().getRecBeans(id);
			if (!beans.isEmpty()) {
				session.setAttribute("jdbeans", beans);
				session.setAttribute("findrec", "ok");
			}
			return SUCCESS;
		}
		// 登陆错误
		return INPUT;
	}

	private void uodataShopItem(String search2, List<JDBean> beans) {
		List<String> contents = JDCrawler.creatTBCrawler()
				.searchObject(search2);
		for (String content : contents) {

			ShopBean bean = (JDBean) JDFactory.getInstance().createShopImfBean(
					content);
			String id = ((JDBean) bean).getShopID();
			String det = ((JDBean) bean).getDeteil_url();
			bean = det.compareTo("null") != 0 ? JDFactory.getInstance()
					.configShopCommentBean(
							JDCrawler.creatTBCrawler().searchComment(id, "1"),
							bean) : null;
			if (bean != null) {
				((JDBean) bean).setShopType(search);
				new JDDao().insertShop(bean);
				beans.add((JDBean) bean);
			}
		}
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

}