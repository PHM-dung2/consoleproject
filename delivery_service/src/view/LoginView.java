package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import controller.LoginViewController;
import network.DSTask;

import model.dto.MemberDto;

public class LoginView extends DSTask {
	public LoginView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void index() throws IOException {
		LoginViewController loginViewController = LoginViewController.getInstance();
		MemberDto member;
		
		int retryCount = 3; // 로그인 재시도 최대 회수는 3회
		
		for (int i = 0; i < retryCount; i++) {
			print("ID: "); String id = next();
			print("Password: "); String password = next();
			member = loginViewController.login(id, password);
			if (member != null) {
				println("로그인 성공");
				switch (member.getType()) {
				case 1:
					new AdminView(clientSocket, reader, writer, member).index();
					return;
				case 2:
					new FranchiseView(clientSocket, reader, writer, member).index();
					return;
				case 3:
					new CustomerView(clientSocket, reader, writer, member).index();
					return;
				}
			}
			println("ID or Password 정보가 일치하지 않습니다. 다시 입력해주세요.");
		}
		
		println("최대입력횟수(3회)를 초과하였습니다. 연결을 끊습니다.");
		try {
			Thread.sleep(2000); // 2초간 메시지 보여주고 연결을 끊는다.
		} catch (InterruptedException e) {
			System.err.println("인터럽트 감지되어 즉시 연결 종료합니다.");
		} finally {
			logout();	
		}
	}
}
