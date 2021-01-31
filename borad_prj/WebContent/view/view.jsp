<%@page import="com.vo.ViewVO"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="http://localhost:8090/borad_prj/css/bootstrap.css">
			<link rel="stylesheet" href="css/custom.css">
			<title>Insert title here</title>
		</head>
		<body>
		
			<%@include file="../nav/nav.jsp" %>
			<%
				//view page에 요청하는 브라우저는 query문자열로 bbsID를 넘겨준다.
				int bbsID = 0;
				if(request.getParameter("bbsID")!=null){
					bbsID = Integer.parseInt(request.getParameter("bbsID"));
				}else{
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('비 정상적인 접근입니다. 잠시 후 다시 사용해주세요.')");
					script.println("history.back()"); //이전 board 페이지로 이동 시킨다.
					script.println("</script>");
				}//end else
				
				BoardDAO bDAO = BoardDAO.getInstance();
				ViewVO vVO = bDAO.view(bbsID);
			%>
			
			<div class = "container">
				<div class ="row">
						<table class="table table-striped" style= "text-align :center;  border: 1px solid #dddddd">
							<thead>
								<tr>
									<th  colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="width: 20%">글제목</td>
									<%
										String title = vVO.getBbsTitle().replaceAll(" ", "&nbsp").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>");
									%>
									<td colspan="2"><%=title%></td>
								</tr>
								<tr>
									<td style="width: 20%">작성자</td>
									<td colspan="2"><%=vVO.getBbsuserID()%></td>
								</tr>
								<tr>
									<td style="width: 20%">작성일자</td>
									<td colspan="2"><%=vVO.getBbsDate()%></td>
								</tr>
								<tr>
									<td>내용</td>
									<%
										String content =vVO.getBbsContent().replaceAll(" ", "&nbsp").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>");
									%>
									<td colspan="2" style="min-height: 200px; text-align: left;"><%=content%></td>
								</tr>
							</tbody>
						</table>
						<a href="http://localhost:8090/borad_prj/board/board.jsp" class="btn btn-primary">목록</a>
						<%
							//session의 userID의 값이 null이 아니고 현재 접속되어 있는 session의 값과 글의 작성자의 값이 동일하다면 수정 삭제 기능 제공
							if(userID != null && userID.equals(vVO.getBbsuserID())){ 
						%>
							<a href="http://localhost:8090/borad_prj/update/update.jsp?bbsID=<%=bbsID%>" class="btn btn-primary">수정</a>
							<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="http://localhost:8090/borad_prj/delete/deleteAction.jsp?bbsID=<%=bbsID%>" class="btn btn-primary">삭제</a>
						<%
							}//end if
						%>
				</div>
			</div>
		</body>
</html>