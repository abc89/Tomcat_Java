<%@page import="com.myweb.bean.*"%>
<%@page import="java.util.List"%>
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
				<%if (null==session.getAttribute("jdbeans")){
	 out.println("<div class='col-lg-6'>"+
				"<h4>sorry no any article</h4>"+
	"<p>.......</p></div>");
	//return;	
}
else{
	UserBean user=(UserBean)session.getAttribute("user");
	 out.println("<div class='col-lg-6'><h4>猜猜您喜欢什么商品</h4>"+
				"<h4>用户"+user.getUserName()+"猜您喜欢：</h4>"+
	"<p></p></div>");
	 List<JDBean> list=(List<JDBean>)session.getAttribute("jdbeans");
	 for(int i=0;i<list.size();i++){
		 JDBean bean=list.get(i);
		 out.println("<img alt='140x140' src='"+bean.getImg_url()+
					"'/><a href='ShopCommentAc.action?id="+bean.getShopID()+"'"+">"+"<h3>介绍："+bean.getTitle()+"</h3></a></>"+"<h3>价格："+bean.getPrice()+"</h3>"+"<h3>好评数："+bean.getGoodCount()+"</h3>");
	 }
	 list.clear();
	}
%>
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
</body>
<%@ include file="foot.jsp"%>
</html>
