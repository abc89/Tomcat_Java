package com.myweb.dao;

import java.util.List;

import com.myweb.bean.ShopCommentBean;


public interface ShopComDaoIntf extends ShopDaoInfo {
     List<ShopCommentBean> getComments(String shopID);
}
