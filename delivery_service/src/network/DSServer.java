package network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.sql.SQLException;

import model.dao.Dao;

public class DSServer {
    private static final int PORT = 5060; // 서버 포트 번호
    private static final int THREAD_POOL_SIZE = 10; // 스레드풀 크기
    
    // 데이터베이스 연동 확인
    public static boolean isDbOk() {
    	return Dao.getInstance().isOK();
    }

    public static void main(String[] args) {
    	// 서버 구동전 데이터베이스 연동 확인
    	if (isDbOk() == false) {
    		System.err.println("!! 데이터베이스 연동 실패 !!");
    		return;
    	}
    	System.out.println("[데이터베이스 연동 성공]");
    	
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE); // 고정 크기 스레드풀 생성

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {        	
            System.out.println("Delivery Service Server is running on port " + PORT);

            while (true) { 
                // 클라이언트 연결 대기
                Socket clientSocket = serverSocket.accept();                
                System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());

                // 클라이언트 요청을 스레드풀에서 처리                
                threadPool.execute(new DSTask(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Server IOException: " + e.getMessage());
        } catch (Exception e) { // 실수로 잡지 못한 예외는 이곳에서 최종 처리
			System.err.println("Unexpected Server Exception: " + e.getMessage());
        } finally {
            threadPool.shutdown(); // 스레드풀 종료
        }
    }
}