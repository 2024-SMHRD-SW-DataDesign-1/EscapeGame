package game;

import java.util.Scanner;

public class Game4 {

	Scanner sc = new Scanner(System.in);
	AnswerCheck AnsC = new AnswerCheck();

	public boolean g4() {
		
		System.out.println();
		System.out.println("for(int i = 0; i < 5; i++) {\r\n" + "	for(int j = 0; j < i ; j++) {\r\n"
				+ "		System.out.print(\" \");	 -->	*****\r\n" + "	}					 ****\r\n"
				+ "	for(int j = 5; j > i; j'??') {		  ***\r\n"
				+ "		System.out.print(\"*\");		   **\r\n" + "	}					    *\r\n"
				+ "	System.out.println();\r\n" + "}");

//		System.out.println("결과:   *****\r\n"
//				+ "	****\r\n"
//				+ "	 ***\r\n"
//				+ "	  **\r\n"
//				+ "	   *");
		
		System.out.println();
		System.out.println("		?? 를 채우시오.");
		

		int answer = 0;
		
		boolean result = AnsC.AnswerC(answer);
		System.out.println();
		return result;

	}

}
