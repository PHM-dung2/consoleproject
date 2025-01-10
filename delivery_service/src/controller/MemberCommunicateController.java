package controller;

import java.io.PrintWriter;
import java.util.ArrayList;

// 접속한 멤버에게 메시지 전달 정도만 할 수 있는 클래스
// (따로 통신 프로토콜 설계 및 클라이언트 프로그램을 운영하는게 아니고, 
//  단순 텍스트 주고받는 용도를 위해서 putty 를 쓰기 때문에 한계가 좀 있다.)

class Comm {
	public String id;
	public PrintWriter writer;

	public Comm(String id, PrintWriter writer) {
		this.id = id;
		this.writer = writer;
	}
}

public class MemberCommunicateController {
	// 접속한 클라이언트들 쓰기 소켓 리스트
	private ArrayList<Comm> commList;

	// 싱글턴
	private MemberCommunicateController() {
		commList = new ArrayList<Comm>();
	}

	private static MemberCommunicateController instance = new MemberCommunicateController();

	public static MemberCommunicateController getInstance() {
		return instance;
	}

	private PrintWriter getWriter(String id) {
		for (Comm comm : commList) {
			if (comm.id.equals(id)) {
				return comm.writer;
			}
		}
		return null;
	}

	// 로그인 된 id 로 가정(이미 DB 에서 검증완료)하기 때문에 들어온 인자값들은 신뢰한다.
	public void addComm(String id, PrintWriter writer) {
		commList.add(new Comm(id, writer));
	}

	public final void println(String id, String content) {
		PrintWriter writer = getWriter(id);
		writer.println(content);
		writer.flush();
	}

	public final void print(String id, String content) {
		PrintWriter writer = getWriter(id);
		writer.print(content);
		writer.flush();
	}

	public final void printf(String id, String format, Object... args) {
		PrintWriter writer = getWriter(id);
		writer.printf(format, args);
		writer.flush();
	}
}
