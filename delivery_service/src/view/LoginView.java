package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import controller.LoginViewController;
import controller.MemberCommunicateController;
import network.DSTask;
import util.DSCrypto;
import model.dto.MemberDto;

public class LoginView extends DSTask {
	public LoginView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void index() throws IOException, InterruptedException {
		LoginViewController loginViewController = LoginViewController.getInstance();
		MemberDto member;

		int retryCount = 3; // 로그인 재시도 최대 회수는 3회

		for (int i = 0; i < retryCount; i++) {
			print("ID: ");
			String id = next();
			print("Password: ");
			String password = next();
			member = loginViewController.login(id, password);

			if (member != null) {
				// 중복 로그인 방지
				if (MemberCommunicateController.getInstance().hasComm(id)) {
					printf("\r\n이미 로그인 된 아이디입니다. 2초후 연결 종료합니다.\r\n");
					Thread.sleep(2000); // 2초간 메시지 보여주고 연결을 끊는다.
					close();
					return;
				}

				println("\r\n로그인 성공하였습니다.");
				// 클라이언트 비정상 종료시 로그인 세션 제거를 위한
				MemberCommunicateController.getInstance().addSockId(clientSocket, id);
				// 통신 멤버 추가
				MemberCommunicateController.getInstance().addComm(id, writer);
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

		println("\r\n** 최대입력횟수(3회)를 초과하였습니다. 2초후 연결 종료합니다. **\r\n");
		Thread.sleep(2000); // 2초간 메시지 보여주고 연결을 끊는다.
		close(); // 로그인 되어 있지 않으므로 close() 로 처리한다.
	}
}
