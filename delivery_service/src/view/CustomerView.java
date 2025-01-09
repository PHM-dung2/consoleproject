package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
		println("1.배달음식검색 2.로그아웃");
		int choose = nextInt(1, 2);

		if (choose == 1) {

		}
		else if (choose == 2) {

		}
	}
}
