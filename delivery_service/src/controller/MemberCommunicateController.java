package controller;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import util.DSLogger;

public class MemberCommunicateController {
	// 접속한 클라이언트들 쓰기 소켓 해시맵
	private ConcurrentHashMap<String, PrintWriter> commList; // id, PrintWriter

	// 접속한 클라이언트 프로그램(putty)을 강제 종료한다던가 할때, 해당하는 commList 의 키값을 가져오기 위한 해시맵
	// key 값은 Socket 이고 value 값이 id 이다. commList.delComm() 메소드의 인자값으로 사용될 값이다.
	private ConcurrentHashMap<Socket, String> sockIdList; // Socket, id

	private MemberCommunicateController() {
		commList = new ConcurrentHashMap<>();
		sockIdList = new ConcurrentHashMap<>();
	}

	private static MemberCommunicateController instance = new MemberCommunicateController();

	public static MemberCommunicateController getInstance() {
		return instance;
	}

	private PrintWriter getWriter(String id) {
		return commList.get(id);
	}

	public void addComm(String id, PrintWriter writer) {
		commList.put(id, writer);
	}

	public void delComm(String id) {
		if (id != null) commList.remove(id);
	}

	// 로그인 되어 있으면 true, 아니면 false
	public boolean hasComm(String id) {
		return commList.get(id) != null ? true : false;
	}

	public void addSockId(Socket socket, String id) {
		sockIdList.put(socket, id);
	}

	public void delSockId(Socket socket) {
		sockIdList.remove(socket);
	}

	public String getSockId(Socket socket) {
		return sockIdList.get(socket);
	}

	public final void println(String id, String content) {
		PrintWriter writer = getWriter(id);
		if (writer != null) {
			writer.println(content);
			writer.flush();
		} else {
			DSLogger.debug("%s writer is null\n", id);
		}
	}

	public final void print(String id, String content) {
		PrintWriter writer = getWriter(id);
		if (writer != null) {
			writer.print(content);
			writer.flush();
		} else {
			DSLogger.debug("%s writer is null\n", id);
		}
	}

	public final void printf(String id, String format, Object... args) {
		PrintWriter writer = getWriter(id);
		if (writer != null) {
			writer.printf(format, args);
			writer.flush();
		} else {
			DSLogger.debug("%s writer is null\n", id);
		}
	}
}