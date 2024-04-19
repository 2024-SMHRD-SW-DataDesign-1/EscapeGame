package chapter;

import java.util.Scanner;

import game.Game1;
import lockDoor.LockDoor;

public class Chap1 extends Chap  {

	public int performAction() {

		Scanner sc = new Scanner(System.in);
		Chap0 c0 = new Chap0();
		Game1 game1 = new Game1();

		int result = 0;

		while (true) {
			// if choose 1번선택지...
			// 1번 구역으로 접근 시 2가지의 선택지가 다시 출력
			System.out.println("선생님의 컴퓨터로 갔으나 모니터는 비밀번호가 걸어져있다. 힌트도 있으며 내용은 다음과 같았다.\n");
			System.out.println("악마의 숫자를 의미하는 3자리 수를 입력하라. 단 숫자는 전부 동일하다.");
			// 정답 : 666

			System.out.println();

			System.out.print("정답 입력 >> ");
			int answer = sc.nextInt();

			if (answer == 666) {
				// 정답일 경우 바로 첫번째 문제를 출력시킨다
				System.out.println();
				System.out.println("정답이다. 라고 생각한 순간 컴퓨터가 열렸다.");
				System.out.println(" ");
				if (game1.g1())
					result = 1;
				
				break;

			} else {
				System.out.println("오답이다. 문제를 다시 풀어봐야겠다...");

			}
		}
		return result;
	}

}
