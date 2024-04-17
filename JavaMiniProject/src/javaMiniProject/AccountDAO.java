package javaMiniProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountDAO {

	Scanner sc = new Scanner(System.in);

	Connection conn = null;
	PreparedStatement psmt = null;

	// 연결
	public void conn() {
		System.out.println(" ");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String user = "campus_24SW_DD_p1_4";
			String password = "smhrd4";

			conn = DriverManager.getConnection(url, user, password);

			// 테스트용. 배포 시 삭제
//			if (conn != null) {
//				System.out.println("DB 연결에 성공하였습니다!! \\(=▽=)/");
//				System.out.println(" ");
//			} else {
//				System.out.println("DB 연결에 실패하였습니다.. (=▽=)...");
//				System.out.println(" ");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 닫기
	public void dbClose() {
		try {
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 로그인
	public boolean logIn() {
		conn();
		String Checkpw = null;
		String name = null;
		ResultSet rs = null;
		boolean result = false;

		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();

		try {
			String sql = "SELECT 비밀번호, 이름 FROM 회원가입 WHERE 아이디=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				Checkpw = rs.getString(1);
				name = rs.getString(2);
			}

			if (pw.equals(Checkpw)) {
				result = true;
				System.out.println("로그인에 성공했습니다.");
				System.out.println(name + "님 환영합니다! \\(=▽=)/");
			} else {
				result = false;
				System.out.println("아이디 혹은 비밀번호가 다릅니다.");
				System.out.println("");
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;

	}

	// 회원 가입
	public void signIn() {
		conn();
		
		String id = null;

		while (true) {

			ResultSet rs = null;
			String sql = "SELECT * FROM 회원가입 WHERE 아이디=?";

			try {
				System.out.print("아이디 : ");
				id = sc.next();
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);

				rs = psmt.executeQuery();

				if (rs.next() == true) {
					System.out.println("이미 가입된 아이디입니다.");
					System.out.println(" ");
					continue;
				} else {
					break;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}

		System.out.print("비밀번호 : ");
		String pw = sc.next();
		System.out.print("이름 : ");
		String name = sc.next();

		String sql = "INSERT INTO 회원가입 VALUES(?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			
			int row = psmt.executeUpdate();

			if (row > 0) {
				System.out.println("회원가입이 성공적으로 되었습니다! \\(=▽=)/");
			} else {
				System.out.println("회원가입에 실패하였습니다... (=▽=)...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

	}

	// 회원 정보 수정
	public void updateUser() {
		conn();
		
		String sql = "UPDATE 회원가입 SET 비밀번호=?, 이름=? WHERE 아이디=?";

		System.out.print("아이디 입력 : ");
		String id = sc.next();
		System.out.print("비밀번호 변경 : ");
		String pw = sc.next();
		System.out.print("이름 변경 : ");
		String name = sc.next();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, name);
			psmt.setString(3, id);

			int row = psmt.executeUpdate();

			if (row > 0) {
				System.out.println("회원 정보 수정이 성공적으로 되었습니다! \\(=▽=)/");
			} else {
				System.out.println("회원 정보 수정에 실패하였습니다... (=▽=)...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

	}

	// 회원 정보 삭제
	public void deleteUser() {
		conn();
		
		System.out.print("삭제할 ID 입력 : ");
		String id = sc.next();

		String sql = "DELETE FROM 회원가입 WHERE 아이디=?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int row = psmt.executeUpdate();

			if (row > 0) {
				System.out.println("회원 정보 삭제가 성공적으로 되었습니다! \\(=▽=)/");
			} else {
				System.out.println("회원 정보 삭제에 실패하였습니다... (=▽=)...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

	}
	
	
	
	// 회원 정보 조회
	public void searchMembers() {
		conn();
		
		ResultSet rs = null;
		String sql = "SELECT * FROM 회원가입";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

	}
	
	
	
	
	
	
	
	

}
