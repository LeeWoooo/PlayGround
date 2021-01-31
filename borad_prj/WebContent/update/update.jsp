<%@page import="com.vo.ViewVO"%>
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
			<title>JSP게시판</title>
		</head>
		<body>
			<%@include file="../nav/nav.jsp" %>
			<%
				int bbsID = Integer.parseInt(request.getParameter("bbsID"));
			
				BoardDAO bDAO = BoardDAO.getInstance();
				ViewVO vVO = bDAO.view(bbsID);
			%>
			<div class = "container">
				<div class ="row">
				<form action="updateAction.jsp?bbsID=<%=bbsID%>" method="post">
						<table class="table table-striped" style= "text-align :center;  border: 1px solid #dddddd">
							<thead>
								<tr>
									<th  colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글 수정 양식</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50" required="required" value="<%=vVO.getBbsTitle()%>"></td> <!-- 현재 타이틀  -->
								</tr>
								<tr>
									<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px" required="required"><%=vVO.getBbsContent()%></textarea></td>
								</tr>
							</tbody>
						</table>
						<a href="http://localhost:8090/borad_prj/board/board.jsp" class="btn btn-primary">목록</a>
						<input type="submit" class="btn btn-primary pull-right" value="글수정">
					</form>
				</div>
			</div>
		</body>
</html>