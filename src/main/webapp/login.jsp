<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src=".js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/bootstrap-combined.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style>
form{
text-align:center;
}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form name="form1" action="GoLogin.action" method="post"
									onSubmit="return mycheck()">
											<td align="left"><select name="type" id="Type">
													<option value="系统管理员">请选择</option>
													
											</select></td>
				<fieldset>
					 <span style="color: red;"> <%if(request.getAttribute("msg")!=null){%>
													<%=request.getAttribute("msg")%> <%}%>
											</span>
											<label>用户名：</label>
					 <input type="text" name="username" id="username" /> 
					 <label>密码：</label>
					 <input type="password" name="password"  id="password"  /> 
					 <span class="help-block">
					 <a href="regist.jsp">还未注册？点此开始你的征途吧</a>
					 </span> 
					 <button type="submit" class="btn">登陆</button><button href="home.jsp" class="btn">取消</button>
				</fieldset>
			</form>
		</div>
	</div>
</div>
</body>
</html>
						