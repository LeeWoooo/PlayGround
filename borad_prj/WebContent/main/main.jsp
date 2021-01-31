<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="http://localhost:8090/borad_prj/css/bootstrap.css">
			<link rel="stylesheet" href="css/custom.css">
			<title>JSP 게시판</title>
		</head>
		<body>
			<%@include file="../nav/nav.jsp" %>
			<div class="container">
				<div class="jumbotron">
						<h1>웹 사이트 소개</h1>
						<p>이 웹 사이트는 부트스트랩으로 만든 JSP 웹사이트 입니다.<br>최소한의 간단한 로직을 이용하여 개발하였습니다. <br>디자인 템플릿으로는 부트 스트랩을 사용하였습니다.</p>
						<p><a class="btn btn-primary btn-pull" href="https://github.com/LeeWoooo" role="button" target="_blank">LeeWooo의 깃허브</a></p>
					</div>
			</div>
			
		</body>
</html>