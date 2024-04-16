package javaMiniProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
	
	public void account() {
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();

		System.out.println("이 게임이 처음이시라면? >> [1]회원가입하기");
		System.out.println("이미 회원가입이 되어있으시다면? >> [2]로그인하기");

		while (true) {
			try {
				System.out.print(">> ");
				int choice = sc.nextInt();
				if (choice == 1) {
					dao.conn();
					dao.signIn();
					dao.dbClose();
					break;
				} else if (choice == 2) {
					dao.conn();
					dao.logIn();
					dao.dbClose();
					break;
				} else {
					System.out.println("비정상적인 접근입니다.");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("비정상적인 접근입니다.");
				sc.nextLine();
				continue;
			}
		}
		sc.close();
	}
	

}
