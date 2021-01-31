<%@page import="java.io.PrintWriter"%>
<%@page import="com.dao.BoardDAO"%>
<%@page import="com.vo.WriteVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>글쓰기를 처리하는 페이지</title>
		</head>
		<body>
		
			<%!
				private static final int WRITESUCCESS = 1;
				private static final int WRITEFAIL = -1;
			%>
		
			<%
				String bbsTitle = request.getParameter("bbsTitle");
				String bbsuserID = (String)session.getAttribute("userID");
				String bbsContent = request.getParameter("bbsContent");
				
				WriteVO wVO = new WriteVO(bbsTitle,bbsuserID,bbsContent);
				BoardDAO bDAO = BoardDAO.getInstance();
				int result = bDAO.write(wVO);
				
				PrintWriter script = response.getWriter();
				
				switch(result){
					case(WRITESUCCESS):{
						script.println("<script>");
						script.println("alert('글이 정상적으로 등록되었습니다')");
						script.println("location.href='http://localhost:8090/borad_prj/board/board.jsp'");
						script.println("</script>");
						break;
					}
					case(WRITEFAIL):{
						script.println("<script>");
						script.println("alert('글쓰기에 실패하였습니다 잠시 후 다시 사용해주세요.')");
						script.println("histroy.back()");
						script.println("</script>");
					}
				}//end catch
				
				
			%>
		</body>
</html>