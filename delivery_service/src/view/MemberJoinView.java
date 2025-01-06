package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import controller.MemberJoinController;
import network.DSTask;

import model.dto.MemberDto;

public class MemberJoinView extends DSTask {
	public MemberJoinView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void index() throws IOException {
		// TODO: 입력값 유효성 확인
		println("----- 회원가입 -----");
		print("ID: "); String id = next(); // TODO: 중복 id 확인
		print("Password: "); String password = next();
		print("이름: "); String name = next();
		print("전화번호: "); String telno = next();
		print("주소: "); String address = next();
		print("가입유형(1: 관리자, 2: 가맹회원, 3: 일반회원): "); int type = nextInt(1, 3);
		
		// 입력받은 값들을 MemberDto 에 넣는다.
		MemberDto member = new MemberDto(id, password, name, telno, address, type);		
		
		// 컨트롤러 싱글턴 객체를 통해 DB 에 insert 한다. insert 성공하면 true 다.
		if (MemberJoinController.getInstance().join(member)) {
			println("----- 회원가입 성공 -----\n");	
		}
		else {
			// TODO: 실패이유 출력하기 - 중복 id 등..
			println("!!!!! 회원가입 실패 !!!!!\n");
		}		
		
		// 다시 초기화면으로 간다.
		new IndexView(clientSocket, reader, writer).index();
	}
}
