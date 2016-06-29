package com.myweb.dao;

import java.util.List;

import com.myweb.bean.ShopCommentBean;


public interface ShopComDaoIntf extends ShopDaoIntf {
     List<ShopCommentBean> getComments(String shopID);
}
