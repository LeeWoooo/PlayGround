<%@page import="com.vo.BoardVO"%>
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
			<title>JSP 게시판</title>
		</head>
		<body>
			<%@include file="../nav/nav.jsp" %>
			<%
				PrintWriter script = response.getWriter();
			
				if(userID == null){
					script.println("<script>");
					script.println("alert('로그인을 한 후 이용해주세요.')");
					script.println("location.href='http://localhost:8090/borad_prj/login/login.jsp'");
					script.println("</script>");
				}//end if
				
				int pageNumber = 1;
				//page로 요청이 올 때 query문자열로 넘어온 pageNumber값이 있다면 받아오기
				if(request.getParameter("pageNumber")!=null){ 
					pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				}//end if
			%>
			<div class = "container">
				<div class ="row">
					<table class="table table-striped" style= "text-align :center; border: 1px solid #dddddd">
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">번호</th>
							<th style="background-color: #eeeeee; text-align: center;">제목</th>
							<th style="background-color: #eeeeee; text-align: center;">작성자</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						</tr>
						<tbody>
								<%
									//db에 있는 게시판 리스트를  조회하여 jsp페이지에 반복문으로 처리해서 뿌려준다.
									//pageNumber을 query문자열로 넘겨줘서 페이지 마다 다른 결과를 보이게 한다.
									//pageNumber을 넘겨줌으로 게시판에 페이징 역할을 한다.
									BoardDAO bDAO = BoardDAO.getInstance();
									List<BoardVO> list = bDAO.loadBoard(pageNumber);
									for(BoardVO bVO : list){ 
								%>
									<tr>
										<td><%=bVO.getBbsID() %></td>
										<td><a href="http://localhost:8090/borad_prj/view/view.jsp?bbsID=<%=bVO.getBbsID()%>"><%=bVO.getBbsTitle() %></a></td>
										<td><%=bVO.getBbsUserID()%></td>
										<td><%=bVO.getBBSDate()%></td>
									</tr>
								<%
									}//end for
								%>
						</tbody>
					</table>
					<%
					 	//만약 pageNumber가 1보다 크다면 현제 pageNumber보다 이전 page가 존재하는 것이기에 이전 버튼 추가
						if(pageNumber > 1){
					%>
						<a href ="board.jsp?pageNumber=<%=pageNumber -1 %>" class="btn btn-success btn-arraw-left">이전</a>
					<%
						}//end if
					%>
					<%
						//만약 현재 pageNumber보다 1 큰값으로 조회했을 때 결과가 있다면 다음 page가 존재하는 것이기 때문에 다음 버튼 추가
						if(bDAO.nextPage(pageNumber+1)){
					%>
						<a href ="board.jsp?pageNumber=<%=pageNumber +1 %>" class="btn btn-success btn-arraw-left">다음</a>
					<%
						}//end if					
					%>
					<a href="http://localhost:8090/borad_prj/write/write.jsp" class="btn btn-primary pull-right">글쓰기</a>
				</div>
			</div>
		</body>
</html>