package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import network.DSTask;

import model.dto.MemberDto;

public class AdminView extends DSTask {
	private MemberDto loginMember;
	
	public AdminView(Socket clientSocket, BufferedReader reader, PrintWriter writer, MemberDto loginMember) {
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
		while(true) {
			println("\n==================     관리자 페이지     ==================");
			print("1.입점승인 목록 2.입점 목록 3.로그아웃 ");
			int choose = nextInt(1 , 3);
			switch (choose) {
				case 1:
					new ManageView(clientSocket, reader, writer).nonEntry();
					break;
				case 2:
					new ManageView(clientSocket, reader, writer).entry();
					break;
			} //s end
			return;
		}
		
	}// f end
}
