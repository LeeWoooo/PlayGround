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
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<div class="jumbotron" style="padding-top:20px;">
						<form action="loginAction.jsp" method="post"> <!-- 로그인 처리를 loginAction에서 처리해준다.  -->
							<h3 style="text-align:center;">로그인화면</h3>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20" required="required">
							</div>						
							<div class="form-group">
								<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20" required="required">
							</div>
							<input type="submit" class="btn btn-primary form-control" value="로그인">						
						</form>
					</div>
				</div>
			</div>
			
		</body>
</html>