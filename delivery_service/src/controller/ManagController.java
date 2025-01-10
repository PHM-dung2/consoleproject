package controller;

import java.io.IOException;

public class ManagController {
		// 싱글톤
		private static ManagController instance = new ManagController();
		private ManagController() {}
		public static ManagController getInstance() {
			return instance;
		}
		
		public void index() throws IOException {
			// 여기서 메뉴 작성 시작하면 됩니다.
			// println() 함수는 System.out.println() 함수와 같습니다.
			// nextInt() 함수는 
				// Scanner scan = new Scanner(System.in);
				// scan.nextInt(); <-- 이 함수와 같습니다.
			// next() 함수도 똑같습니다.
		}
		
		
		

}
