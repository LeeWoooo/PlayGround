package useclob;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	private static DAO dao;

	private DAO() {
	}

	public static DAO getInstance() {
		if (dao == null) {
			dao = new DAO();
		} // end if
		return dao;
	}// getInstance

	public List<VO> selectclob() throws SQLException {
		List<VO> list = new ArrayList<VO>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pwd = "tiger";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, id, pwd);
			String selectclob = "SELECT SUBJECT, CONTENTS, WRITER, TO_CHAR(INPUT_DATE,'yyyy-dd-mm') INPUT_DATE FROM TEST_CLOB";
			pstmt = con.prepareStatement(selectclob);
			rs = pstmt.executeQuery();
			
			VO vo = null;
			Clob clob = null;
			BufferedReader br = null;
			StringBuilder sb = null;
			String title ="";
			String article ="";
			String repoter ="";
			String inputDate="";
			while(rs.next()) {
				title=rs.getString("SUBJECT");
				repoter = rs.getString("WRITER");
				inputDate = rs.getString("INPUT_DATE");
				clob = rs.getClob("CONTENTS");
				br = new BufferedReader(clob.getCharacterStream());
				sb = new StringBuilder();
				try {
					while((article=br.readLine()) != null) {
						sb = new StringBuilder().append(article);
					}//end while
					article = sb.toString();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}//end catch
				}//end finally
				vo = new VO(title, article, repoter, inputDate);
				list.add(vo);
			}//while
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return list;
	}// selectclob
//
//	public static void main(String[] args) {
//		DAO dao = DAO.getInstance();
//		try {
//			List<VO> list = dao.selectclob();
//			System.out.println(list);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
}// class
