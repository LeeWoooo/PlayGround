<%@page import="com.dao.BoardDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시판 글 삭제를 처리하는 페이지</title>
		</head>
		<body>
		
			<%!
				private static final int DELETESUCCESS = 1;
				private static final int DELETEFAIL = -1;
			%>
			<%
				PrintWriter script = response.getWriter();
				int bbsID = 0;
				if(request.getParameter("bbsID")!=null){
					bbsID = Integer.parseInt(request.getParameter("bbsID"));
				}else{
					script.println("<script>");
					script.println("alert('비 정상적인 접근입니다. 잠시 후 다시 사용해주세요.')");
					script.println("history.back()"); //로그인 성공시 메인페이지로 보낸다.
					script.println("</script>");
				}//end else
			
				BoardDAO bDAO = BoardDAO.getInstance();
				int result = bDAO.delete(bbsID);
			
				switch(result){
					case(DELETESUCCESS):{
						script.println("<script>");
						script.println("alert('정상적으로 삭제되었습니다.')");
						script.println("location.href='http://localhost:8090/borad_prj/board/board.jsp'"); //로그인 성공시 메인페이지로 보낸다.
						script.println("</script>");
						break;
					}
					case(DELETEFAIL):{
						script.println("<script>");
						script.println("alert('글이 삭제되지 않았습니다 잠시 후 다시 사용해주세요.')");
						script.println("history.back()"); //다시 이전 페이지인 로그인 페이지로 보낸다.
						script.println("</script>");
					}
				}//end switch
				
			%>
		</body>
</html>