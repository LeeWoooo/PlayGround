package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vo.JoinVO;

public class UserDAO {
	
	private static UserDAO uDAO;
	
	private UserDAO() {
	}

	public static UserDAO getInstance() {
		if(uDAO == null) {
			uDAO = new UserDAO();
		}// end if
		return uDAO;
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
	
	public int login(String userID, String userPassword) {
		int flag = -2; //DB오류
		String sql = "SELECT USERPASSWORD FROM USERS WHERE USERID =?";
		try(Connection con = uDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userID);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					if(rs.getString("USERPASSWORD").equals(userPassword)) {
						flag = 1; //로그인 성공
						return flag;
					}//end if
					flag = 0;//비밀번호 불일치
					return flag;
				}//end if
				flag = -1;//아이디가 존재하지 않음
				return flag; 
			}//end try
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//end login
	
	public int join(JoinVO jVO) {
		int flag= -1; //이미 아이디가 존재하는 경우
		String sql = "INSERT INTO USERS VALUES (?,?,?,?,?)";
		try(Connection con = uDAO.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, jVO.getUserID());
			pstmt.setString(2, jVO.getUserPassword());
			pstmt.setString(3, jVO.getUserName());
			pstmt.setString(4, jVO.getUserGender());
			pstmt.setString(5, jVO.getUserEmail());
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//join
	
}
