package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import controller.EntryController;
import network.DSTask;

public class EntryView extends DSTask{
	// 접속된 클라이언트 네트워크 소켓을 받아서 그대로 사용하기 위한 생성자
	public EntryView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void entryJoin() throws IOException {
		// 여기서 메뉴 작성 시작하면 됩니다.
		// println() 함수는 System.out.println() 함수와 같습니다.
		// nextInt() 함수는 
			// Scanner scan = new Scanner(System.in);
			// scan.nextInt(); <-- 이 함수와 같습니다.
		// next() 함수도 똑같습니다.
		
		
		boolean result = EntryController.getInstance().entryJoin(entryDto);
	} // f end
	
	public void entryList() throws IOException {
		println("==================     입점신청     ==================");
		println("입점번호\t상호명\t지점명\t\t\t지점주소\t\t\t카테고리");
		
	} // f end
	
	public void menu() throws IOException {
		// 여기서 메뉴 작성 시작하면 됩니다.
		// println() 함수는 System.out.println() 함수와 같습니다.
		// nextInt() 함수는 
			// Scanner scan = new Scanner(System.in);
			// scan.nextInt(); <-- 이 함수와 같습니다.
		// next() 함수도 똑같습니다.
		System.out.println("========== 메뉴신청 ==========");
		System.out.print("상호명\t지점명\t\t지점주소\t\t카테고리");
		
		
	} // f end
	
	
}
