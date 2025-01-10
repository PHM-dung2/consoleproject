package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import controller.MemberCommunicateController;
import network.DSTask;

import model.dto.MemberDto;

public class CustomerView extends DSTask {
	private MemberDto loginMember;

	public CustomerView(Socket clientSocket, BufferedReader reader, PrintWriter writer, MemberDto loginMember) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
		this.loginMember = loginMember;
	}

	public MemberDto getLoginMember() {
		return loginMember;
	}

	public String getLoginId() {
		return loginMember.getId();
	}

	public void index() throws IOException {
		System.out.println(getLoginMember());
		System.out.println(getLoginId());
		println("===== 일반회원 페이지입니다. =====");
		println("1.배달음식검색 2.로그아웃 3.메시지 전달 테스트");
		int choose = nextInt(1, 3);

		if (choose == 1) {
			search();
		}
		else if (choose == 2) {
			logout();
		}
		else if (choose == 3) {
			test();
		}
	}
	
	private void test() throws IOException {
		while (true) {
			print("보낼 메시지(테스트): "); String msg = next();			
			MemberCommunicateController.getInstance().println("fran", msg);	
		}		
	}
	
	private void search() throws IOException {
		String a = "테스트 메시지입니다.";
		int b = 10;
		printf("test : %s %d :\r\n", a, b); // printf 메소드 테스트
		next(); // 위 메시지 출력된거 확인용으로 사용함
	}
}
