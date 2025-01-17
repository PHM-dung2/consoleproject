package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controller.MemberCommunicateController;
import view.IndexView;

public class DSTask implements Runnable {
	public Socket clientSocket;
	public BufferedReader reader;
	public PrintWriter writer;

	public DSTask() { // 상속용으로 작성되었음. 없으면 컴파일 오류 발생..
	}

	public DSTask(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new PrintWriter(clientSocket.getOutputStream(), true);
	}

	public final void close() throws IOException {
		reader.close();
		writer.close();
		clientSocket.close();
	}

	public final void logout(String id) throws IOException {
		MemberCommunicateController.getInstance().delSockId(clientSocket);
		MemberCommunicateController.getInstance().delComm(id);
		close();
	}

	public final int nextIntInter() throws IOException {
		int choose = -1;

		try {
			String line = reader.readLine();
			if (line == null) {
				throw new IOException("nextIntInter(): IO(read) Error");
			}

			choose = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			print("\r\n올바른 값을 다시 입력해주세요.\r\n");
		}

		return choose;
	}

	public final int nextIntInter(int min, int max) throws IOException {
		int choose = -1;

		try {
			String line = reader.readLine();
			if (line == null) {
				throw new IOException("nextIntInter(): IO(read) Error");
			}

			choose = Integer.parseInt(line);
			if (choose < min || choose > max) {
				choose = -1;
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			print("\r\n올바른 값을 다시 입력해주세요.\r\n");
		}

		return choose;
	}

	public final int nextInt() throws IOException {
		int choose;

		while ((choose = nextIntInter()) == -1) {
		}

		return choose;
	}

	public final int nextInt(int min, int max) throws IOException {
		int choose;

		while ((choose = nextIntInter(min, max)) == -1) {
		}

		return choose;
	}

	public final String next() throws IOException {
		String line = reader.readLine();
		if (line == null) {
			throw new IOException("next(): IO(read) Error");
		}

		return line;
	}

	public final void println(String content) throws IOException {
		writer.println(content);
		writer.flush();
	}

	public final void print(String content) throws IOException {
		writer.print(content);
		writer.flush();
	}

	public final void printf(String format, Object... args) throws IOException {
		writer.printf(format, args);
		writer.flush();
	}

	// 클라이언트 비정상 종료시 로그인 세션 제거
	private void logoutSudden() {
		String loginId = MemberCommunicateController.getInstance().getSockId(clientSocket);
		MemberCommunicateController.getInstance().delComm(loginId);
		MemberCommunicateController.getInstance().delSockId(clientSocket);
	}

	@Override // 이 메소드는 상속받은 클래스가 사용하면 안된다. 특정 메소드만 상속 자체를 막을수는 없어서 그냥 두었다.
	public final void run() {
		try {
			new IndexView(clientSocket, reader, writer).index();
		} catch (IOException e) {
			System.err.println("Error handling client: " + e.getMessage());
			logoutSudden();
		} catch (Exception e) {
			System.err.println("Server exception: " + e.getMessage());
			logoutSudden();
		} finally {
			try {				
				close();				
			} catch (IOException e) {
				System.err.println("Error closing client socket: " + e.getMessage());
			}
			System.out.println("Client disconnected: " + clientSocket.getRemoteSocketAddress());
		}
	}
}