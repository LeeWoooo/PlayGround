<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>로그아웃을 처리하는 페이지</title>
		</head>
		<body>
			<%
				session.invalidate();
				
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('로그아웃 되었습니다 메인으로 이동됩니다.')");
				script.println("location.href='http://localhost:8090/borad_prj/main/main.jsp'");
				script.println("</script>");
			%>
		</body>
</html>