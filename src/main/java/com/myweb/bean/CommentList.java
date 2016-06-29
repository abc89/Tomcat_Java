package com.myweb.bean;

import java.util.ArrayList;
import java.util.List;

public class CommentList {
  private static List<String> comments=new ArrayList<String>();
  public void addAll(List<String> list){
	comments.addAll(comments);  
  }
  public List<String> getList(){
	  return comments;
  }
}
