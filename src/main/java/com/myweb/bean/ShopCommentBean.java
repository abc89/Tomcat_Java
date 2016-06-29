package com.myweb.bean;

import java.util.List;

public class ShopCommentBean extends ShopBean{
    private String content;
    private String shopId;
	private List<String> comments;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String getShopType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configComment(List<String> list) {
		for (String string : list) {
			this.content=this.content+string+"a";
		}
		this.comments=list;
	}

	public List<String>  getComments(ShopCommentBean bean) {
		return comments;
	}

	

}
