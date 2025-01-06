package view;

// 실제 사용되는 클래스 아님. 뷰 클래스 초기 모습을 보여주기 위한 클래스.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

// DSTask 클래스 import 해야함.
import network.DSTask;

// DSTask 를 상속받아서 View 클래스를 작성해야한다.
public class TemplateView extends DSTask {
	// 접속된 클라이언트 네트워크 소켓을 받아서 그대로 사용하기 위한 생성자
	public TemplateView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
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