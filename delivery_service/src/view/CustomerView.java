package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import network.DSTask;

public class CustomerView extends DSTask {
	public CustomerView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void index() throws IOException {
		println("===== 일반회원 페이지입니다. =====");
		println("1.배달음식검색 2.로그아웃");
		int choose = nextInt(1, 2);
	}
}
