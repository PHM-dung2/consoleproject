package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import model.dto.EntryDto;
import model.dto.MemberDto;
import network.DSTask;

public class ManagView extends DSTask{
	private MemberDto loginMember;
	
	public ManagView(Socket clientSocket , BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;	
	}
	
	public MemberDto getLoginMember() {
		return loginMember;
	}
	
	public String getLoginId() {
		return loginMember.getId();
	}
	
	public void ManagView() throws IOException {
		//1. 페이지 출력
		println("===== 가맹점 신청목록 =====");
		
		
		
		
		
		println("1.번호선택 2.뒤로가기");
		int choose = nextInt(1,2);
		switch (choose) {
		case 1: 
			
		case 2:
		
		}//s end
		
		
	
		
		
		// println() 함수는 System.out.println() 함수와 같습니다.
		// nextInt() 함수는 
			// Scanner scan = new Scanner(System.in);
			// scan.nextInt(); <-- 이 함수와 같습니다.
		// next() 함수도 똑같습니다.
		
		
		
		
	}// f end
	
	
}// class end
