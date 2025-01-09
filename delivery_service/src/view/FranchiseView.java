package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import network.DSTask;

import model.dto.MemberDto;

public class FranchiseView extends DSTask {
	private MemberDto loginMember;
	
	public FranchiseView(Socket clientSocket, BufferedReader reader, PrintWriter writer, MemberDto loginMember) {
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
		// TODO: 가맹여부 포함한 환영메시지 구현
		while(true) {
			println("==================     입점회원 페이지     ==================");
			print("1.입점신청 2.메뉴등록 3.주문콜대기 4.콜리스트 5.로그아웃 ");
			int choose = nextInt(1, 5);
			switch (choose) {
				case 1:
					new EntryView(clientSocket, reader, writer, getLoginMember() ).entryJoin();
					break;
				case 2:
					new EntryView(clientSocket, reader, writer, getLoginMember() ).menuIndex();
					break;
			}
			
		} // w end
	}
}
