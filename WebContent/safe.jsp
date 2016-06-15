
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.myweb.bean.*" %>
<html>
<title>提醒</title>
<link href="./css/bootstrap.css" rel="stylesheet" media="screen">   
<link href="./css/bootstrap-theme.css" rel="stylesheet" media="screen">  
<body>
<%if (null==session.getAttribute("user")){
	 out.println("<div class='alert alert-warning' role='alert'><a href='login.jsp'>请登录登陆</a></div><script src='./js/bootstrap.js'></script>");
	//return;	
}
else{
	Bean bean=(Bean)session.getAttribute("user");
	if(bean.getType().compareTo(Bean.VIPUSER)==0){
		AdminBean adminBean=(AdminBean)bean;
    out.println("<div class='alert alert-warning' role='alert'>"+"欢迎您："+adminBean.getName()+"</div><script src='./js/bootstrap.js'></script>");
	}
	}
%>

</body>
</html>