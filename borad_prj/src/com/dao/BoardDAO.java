package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vo.BoardVO;
import com.vo.UpdateVO;
import com.vo.ViewVO;
import com.vo.WriteVO;

public class BoardDAO {
	
	private static BoardDAO bDAO;
	
	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		if(bDAO == null) {
			bDAO = new BoardDAO();
		}//end if
		return bDAO;
	}//getInstance
	
	public Connection getConnection() throws SQLException{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pwd = "tiger";
		return DriverManager.getConnection(url, id,pwd);
	}//getConnection
	
	public int write(WriteVO wVO) {
		int flag = -1;
		String sql = "INSERT INTO BBS VALUES ((SELECT NVL(MAX(BBSID),0)+1  BBSID FROM BBS) ,?,?,SYSDATE,?,?)";
		try(Connection con = bDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, wVO.getBbsTitle());
			pstmt.setString(2, wVO.getBbsUserID());
			pstmt.setString(3, wVO.getBbsContent());
			pstmt.setInt(4, 1);
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//write
	
	
	public List<BoardVO> loadBoard(int pageNumber){
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		String sql = new StringBuilder()
				.append("SELECT BBSID,BBSTITLE,BBSUSERID,TO_CHAR(BBSDATE,'yyyy-MM-DD HH24:MI:SS') BBSDATE ,BBSCONTENT, SEQ ")
				.append("FROM ")
				.append("(SELECT BBSID,BBSTITLE,BBSUSERID,BBSDATE,BBSCONTENT,ROW_NUMBER()OVER(ORDER BY BBSDATE DESC) SEQ ")
				.append("FROM BBS WHERE BBSID > 0 AND BBSAVAILABLE =1) ")
				.append("WHERE SEQ > ? AND SEQ <= ?").toString();
		
		try(Connection con = bDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, (pageNumber-1) *10);
			pstmt.setInt(2, pageNumber *10);
			try(ResultSet rs = pstmt.executeQuery()){
				BoardVO bVO = null;
				while(rs.next()) {
					bVO = new BoardVO(rs.getInt("BBSID"), rs.getString("BBSTITLE"), rs.getString("BBSUSERID"), rs.getString("BBSDATE"), rs.getString("BBSCONTENT"));
					list .add(bVO);
				}//end while
			}//end try
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return list;
	}//end loadBoard
	
	//지금 게시판에 조회된 페이지의 pageNumber보다 1큰수를 받아서 조회한 결과 값이 있다면 true를 반환
	public boolean nextPage(int pageNumber) {
		boolean flag = false;
		String sql = new StringBuilder()
				.append("SELECT BBSID, SEQ ")
				.append("FROM (SELECT BBSID, ROW_NUMBER()OVER(ORDER BY BBSDATE DESC) SEQ ")
				.append("FROM BBS WHERE BBSID >0 AND BBSAVAILABLE =1 )")
				.append("WHERE SEQ > ? AND SEQ <=?").toString();
		try(Connection con = bDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, (pageNumber-1) *10);
			pstmt.setInt(2, pageNumber *10);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) { //만약 조회 결과가 있다면 true반환
					flag = true;
					return flag;
				}//end while
			}//end try
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//next page
	
	
	public ViewVO view(int bbsID) {
		ViewVO vVO = null;
		String sql = "SELECT BBSTITLE, BBSUSERID, TO_CHAR(BBSDATE,'YYYY-MM-DD HH24:MI:SS') BBSDATE, BBSCONTENT FROM BBS"
				+ " WHERE BBSID =?";
		try(Connection con = bDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, bbsID);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
				vVO = new ViewVO(rs.getString("BBSTITLE"), rs.getString("BBSUSERID"), rs.getString("BBSDATE"), rs.getString("BBSCONTENT"));
				}
			}//end try
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return vVO;
	}//view
	
	public int delete(int bbsID) {
		int flag = -1; //db오류
		String sql = "UPDATE BBS SET BBSAVAILABLE=0 WHERE BBSID = ?";
		try(Connection con = bDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, bbsID);
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//view
	
	public int update(UpdateVO uVO) {
		int flag  =-1;
		String sql = "UPDATE BBS SET BBSTITLE =?, BBSCONTENT=? WHERE BBSID=?";
		try(Connection con = bDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, uVO.getBbsTitle());
			pstmt.setString(2, uVO.getBbsContent());
			pstmt.setInt(3, uVO.getBbsID());
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//update
	
}
