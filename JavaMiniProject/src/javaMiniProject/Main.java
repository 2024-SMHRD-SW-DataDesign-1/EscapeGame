package javaMiniProject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Account acc = new Account();

		// acc.account();

		// 로그인 유저 아이디 저장
		String id = "wnstj412";

		DAO dao = new DAO();
		PreparedStatement psmt = null;
		String cp = "";
		String tempstr = "";
		ArrayList<String> al = new ArrayList<String>();

		while (true) {

			System.out.print("[1]처음부터 [2]이어하기 >> ");
			int input = sc.nextInt();

			if (input == 1) {
				al.add("칠판");
				al.add("간식통");
				al.add("내PC");
				al.add("선생님PC");
				al.add("뜬금없이놓여있는어항");

			} else if (input == 2) {

				dao.conn();

				ResultSet rs = null;
				String sql = "SELECT USER_SP FROM TB_SAVEP WHERE USER_ID = ?";

				try {

					psmt = dao.conn.prepareStatement(sql);

					psmt.setString(1, id);
					rs = psmt.executeQuery();

					if (rs.next()) {
						tempstr = rs.getString(1);
						String[] splitValues = tempstr.split(",");
						for (String value : splitValues) {
							al.add(value);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					dao.dbClose();
				}

			}

			while (true) {

				// 선택지
				System.out.println("수상한 곳들이 있다..");
				System.out.println("어디를 확인해볼까?");

				for (int i = 0; i < al.size(); i++) {
					System.out.println("[" + (i + 1) + "]" + " " + al.get(i));
				}
				System.out.print(">> ");

//					
//					
//					
//					
//					
//					                              게임 메인 구역
//					
//					
//					
//					
//					

				// 정답 선택지 입력
				int correct = sc.nextInt();

				// 정답 선택지 삭제
				al.remove(correct - 1);

				System.out.println(cp);

				// 선택지 백업
				for (int i = 0; i < al.size(); i++) {
					cp += al.get(i) + ",";
				}

				System.out.println(cp);
				System.out.println(id);

				try {
					System.out.println("try 실행");
					String sql = "UPDATE TB_SAVEP SET USER_SP = ? WHERE USER_ID=?";
					dao.conn();

					ResultSet rs = null;
					String sql2 = "SELECT * FROM TB_SAVEP";
					try {
						psmt = dao.conn.prepareStatement(sql2);
						rs = psmt.executeQuery();

						while (rs.next()) {
							System.out.println(rs.getString(1) + "\t" + rs.getString(2));
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						dao.dbClose();
					}
					
					dao.conn();
					System.out.println("1");
					psmt = dao.conn.prepareStatement(sql);
					System.out.println("2");
					psmt.setString(1, cp);
					System.out.println("3");
					psmt.setString(2, id);
					System.out.println("4");
					
					
					System.out.println(cp);
					System.out.println(id);
					int raw = psmt.executeUpdate();
					System.out.println("5");
					
					if (raw >0) {
						System.out.println("성공");
					} else {
						System.out.println("실패");
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					dao.dbClose();
					System.out.println("fianlly실행");
				}
				break;
			}
		}

	}

}