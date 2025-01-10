package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import network.DSTask;

public class IndexView extends DSTask {
	public IndexView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void index() throws IOException {
		println("\n==================     배달서비스 시스템     ==================");
		print("1. 회원가입 2. 로그인 ");

		int choose = nextInt(1, 2);
		switch (choose) {
		case 1:
			new MemberJoinView(clientSocket, reader, writer).index();
			return;
		case 2:
			new LoginView(clientSocket, reader, writer).index();
			return;
		}
	}
}
