<%@page import="java.io.PrintWriter"%>
<%@page import="com.vo.JoinVO"%>
<%@page import="com.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>회원가입을 처리하는 페이지</title>
		</head>
		<body>
			<%!
				private static final int JOINSUCCESS = 1;
				private static final int EXISTID = -1;
			%>
			
			<%
				//form에서 값 받기
				String userID = request.getParameter("userID");
				String userPassword = request.getParameter("userPassword");
				String userName = request.getParameter("userName");
				String userGender = request.getParameter("userGender");
				String userEmail = request.getParameter("userEmail");
				
				UserDAO uDAO = UserDAO.getInstance();
				JoinVO jVO = new JoinVO(userID,userPassword,userName,userGender,userEmail);
				int result = uDAO.join(jVO);
				
				PrintWriter script = response.getWriter();
				
				switch(result){
					case(JOINSUCCESS):{
						script.println("<script>");
						script.println("alert('회원가입에 성공하였습니다. 로그인 해주세요')");
						script.println("location.href='http://localhost:8090/borad_prj/login/login.jsp'");
						script.println("</script>");
						break;
					}
					case(EXISTID):{
						script.println("<script>");
						script.println("alert('이미 존재하는 아이디 입니다 다른 아이디를 이용해주세요.')");
						script.println("history.back()");
						script.println("</script>");
					}
					
				}//switch
			%>
		</body>
</html>