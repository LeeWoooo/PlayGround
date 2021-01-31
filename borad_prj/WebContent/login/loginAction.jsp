<%@page import="java.io.PrintWriter"%>
<%@page import="com.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>로그인 처리 페이지</title>
		</head>
		<body>
		
			<%!
				private static final int LOGINSUCCESS = 1;
				private static final int PASSWORDERROR= 0;
				private static final int NOTEXISTID= -1;
				private static final int DBERROR= -2;
			%>
		
			<%
			String userID = request.getParameter("userID");
			String userPassword = request.getParameter("userPassword");
					
			UserDAO uDAO = UserDAO.getInstance();
			int result = uDAO.login(userID, userPassword);
			PrintWriter script = response.getWriter();
			
			switch(result){
				case(LOGINSUCCESS):{
					//로그인에 성공할 시 로그인한 아이디로 세션등록
					session.setAttribute("userID", userID);
					script.println("<script>");
					script.println("alert('로그인에 성공하였습니다')");
					script.println("location.href='http://localhost:8090/borad_prj/main/main.jsp'"); //로그인 성공시 메인페이지로 보낸다.
					script.println("</script>");
					break;
				}
				case(PASSWORDERROR):{
					System.out.print(result);
					script.println("<script>");
					script.println("alert('비밀번호를 잘못입력하였습니다. 확인 후 다시 시도해주세요.')");
					script.println("history.back()"); //다시 이전 페이지인 로그인 페이지로 보낸다.
					script.println("</script>");
					break;
				}
				case(NOTEXISTID):{
					script.println("<script>");
					script.println("alert('아이디가 존재하지 않습니다.확인 후 다시 시도해주세요.')");
					script.println("history.back()"); //다시 이전 페이지인 로그인 페이지로 보낸다.
					script.println("</script>");
					break;
				}
				case(DBERROR):{
					script.println("<script>");
					script.println("alert('로그인 중 오류가 발생하였습니다. 잠시후 다시 시도해주세요')");
					script.println("history.back()"); //다시 이전 페이지인 로그인 페이지로 보낸다.
					script.println("</script>");
				}
			}//switch
			
			%>
		</body>
</html>