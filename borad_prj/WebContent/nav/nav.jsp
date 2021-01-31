<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<title>JSP 게시판</title>
		</head>
		<body>
			<%
				String userID = null; //세션을 받을 변수를 선언
				if(session.getAttribute("userID") != null){ //만약 userID의 key에 해당하는 value가 있다면
					userID = (String)session.getAttribute("userID"); //세션을 받을 변수에 value값을 선언한다.
				}//end if
				//System.out.println(userID);
			%>
			<nav class="navbar navbar-default">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>				
					<a class="navbar-brand" href="main.jsp">JSP 게시판</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="http://localhost:8090/borad_prj/main/main.jsp">메인</a>
						<li><a href="http://localhost:8090/borad_prj/board/board.jsp">게시판</a>
					</ul>
					<%
						//로그인이 되어있어 이미 세션이 있다면 보여지는 화면을 다르게 해야 한다.
						//로그인이 되어있는 상태에서 접속하기나 로그인 회원가입을 보여줄 필요가 없다.
						//그렇기 때문에 세션에서 얻어온 userID가 없다면 접속하기,로그인,회원가입을 보여주고
						//그렇지 않다면 회원관리 및 로그아웃을 보여준다.
						if(userID == null){
					%>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">접속하기<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="http://localhost:8090/borad_prj/login/login.jsp">로그인</a></li>
								<li><a href="http://localhost:8090/borad_prj/join/join.jsp">회원가입</a></li>
							</ul>
						</li>
					</ul>
					<%
						}else{ //만약 세션이 존재한다면
					%>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">회원관리<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="http://localhost:8090/borad_prj/logout/logout.jsp">로그아웃</a></li>
							</ul>
						</li>
					</ul>
					<%
						}//end else
					%>
				</div>
			</nav>
			<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
			<script src="http://localhost:8090/borad_prj/js/bootstrap.js"></script>
		</body>
</html>