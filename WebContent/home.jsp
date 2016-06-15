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
			<div class="jumbotron well">
				<h1>
					个人纪录
				</h1>
				<p class="lead">
					阳光明媚
				</p>
			</div>
		
		</div>
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
					<a href="WriteArticleAc.action">写日记</a>
				</li>
				<li class="nav-header">
					功能
				</li>
				<li>
					<a href="#">他人文章</a>
				</li>
				<li class="divider">
				</li>
				<li>
					<a href="#">开始新文章</a>
				</li>
				<li>
					<a href="#">资料</a>
				</li>
				<li>
					<a href="#">设置</a>
				</li>
			</ul> <span class="label">文字标签</span>
			
		</div>
	</div>
</div>
</body>
<%@ include file="foot.jsp"%>
</html>
