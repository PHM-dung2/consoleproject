package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import network.DSTask;

public class AdminView extends DSTask {
	public AdminView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void index() throws IOException {
		println("===== 관리자 페이지입니다. =====");
		println("1.가맹신청목록 2.가맹점 목록 3.로그아웃");
		int choose = nextInt(1 , 3);
		switch (choose) {
		
		case 1:
			new ManagView(clientSocket, reader, writer).index();
			return;
		
		case 2:
			
			
		case 3:
		
		} //s end
	}// f end
}
