<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
		</head>
		<body>
			<%
				//index페이지로 들어오게 되면 메인페이지로 리다이렉트	
				response.sendRedirect("http://localhost:8090/borad_prj/main/main.jsp"); 
			%>
		</body>
</html>