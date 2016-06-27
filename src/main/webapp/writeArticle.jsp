<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="backGround.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src=".js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/bootstrap-combined.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style>
.row-marketing{
margin :80px;
}
</style>
</head>
<body >
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span8">
		 <span style="color: red;"> <%if(request.getAttribute("msg")!=null){%>
													<%=request.getAttribute("msg")%> <%}%>
											</span>
	<form action="ArticleAction.action?type=insert" method="post"
									onSubmit="return mycheck()">
<fieldset><legend contenteditable="true">新纪录</legend> <label contenteditable="true">标题：</label> <input placeholder="Type something…" name="title"  id="title" style="width:560px; height:30px; float:center;" type="text"><legend></legend> <label contenteditable="true">文章描述：</label> <textarea style="width:560px;" name="dec"  id="dec" height:60px; float:center;" type="text"></textarea><legend contenteditable="true"></legend> <label contenteditable="true">正文：</label>
<p><textarea rows="5" cols="30" name="content"  id="content" style="width:560px; height:700px; float:center;" type="text"></textarea></p>

<div>&nbsp;</div>
<legend></legend>

<p><button class="btn" contenteditable="true" style="float:center;" type="submit">提交</button></p>
</fieldset>
</form>
		
		</div>
		<div class="span4">
			<div class="span4">
			<ul class="nav nav-list">
				<li class="nav-header">
					我的导航
				</li>
				<li class="active">
					<a href="home.jsp">首页</a>
				</li>
				<li>
					<a href="ArticleAction.action?type=data">文章</a>
				</li>
				<li>
					<a href="writeArticle.jsp">写日记</a>
				</li>
				<li class="nav-header">
					功能
				</li>
				<li>
					<a href="RecAction.action">查看推荐</a>
				</li>
				<li>
						<a href="test.jsp">测试</a>
				</li>
				<li class="divider">
				</li>
			
			</ul> <span class="label"></span>
			</div>
		</div>
	</div>
</div>
</body>
<%@ include file="foot.jsp"%>
</html>
