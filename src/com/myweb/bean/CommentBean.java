package com.myweb.bean;
/**
 * 用户评 论数据模型
 * @author e7691
 *
 */
public class CommentBean {
  private String id;
  private String fenShu;
  private String content;
  /**
   * 
   * @param id 用户id
   * @param fenShu 用户评分分数
   * @param content 用户评价内容
   */
  public CommentBean(String id,String fenShu,String content){
	  this.fenShu=fenShu;
	  this.id=id;
	  this.content=content;
  }
  public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getFenShu() {
	return fenShu;
}
public void setFenShu(String fenShu) {
	this.fenShu = fenShu;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
}
