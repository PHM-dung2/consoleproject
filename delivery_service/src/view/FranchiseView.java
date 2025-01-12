package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import controller.FranController;
import controller.FranchiseController;
import network.DSTask;

import model.dto.MemberDto;
import model.dto.OrderCompleteDto;

public class FranchiseView extends DSTask {
	private MemberDto loginMember;

	public FranchiseView(Socket clientSocket, BufferedReader reader, PrintWriter writer, MemberDto loginMember) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
		this.loginMember = loginMember;
	}

	public MemberDto getLoginMember() {
		return loginMember;
	}

	public String getLoginId() {
		return loginMember.getId();
	}

	public int getLoginMno() {
		return loginMember.getMno();
	}

	public void index() throws IOException {
		// TODO: 가맹여부 포함한 환영메시지 구현
		while (true) {
			println("\n==================     입점회원 페이지     ==================");
			print("1.입점신청 2.메뉴등록 3.주문콜대기 4.주문완료목록 5.로그아웃 ");
			int choose = nextInt(1, 5);
			switch (choose) {
			case 1:
				new EntryView(clientSocket, reader, writer, getLoginMember()).entryJoin();
				break;
			case 2:
				new EntryView(clientSocket, reader, writer, getLoginMember()).menuIndex();
				break;
			case 3:
				waitCall();
				break;
			case 4:
				orderCompleteList();
				break;
			case 5:
				logout(getLoginId());
				return; // 로그아웃은 break 아닌 return 해서 접속을 끊는다.
			}
		} // w end
	}

	public void waitCall() throws IOException {
		println("\r\n주문 대기중입니다... (q 입력시 메뉴로 돌아갑니다.)");
		while (!(next().equals("q"))) {
		}
	}

	public void orderCompleteList() throws IOException {
		ArrayList<OrderCompleteDto> orderCompleteList = new ArrayList<>();

		orderCompleteList = FranchiseController.getInstance().getOrderCompleteList(getLoginMno());
		
		// 주문일 기준 오름차순 정렬
		orderCompleteList.sort(Comparator.comparing(OrderCompleteDto::getOrderDate));

		println("\r\n------------------      주문완료목록      ------------------");
		println("번호 주문자 주문일 주문메뉴 주문가격");

		int i;
		for (i = 0; i < orderCompleteList.size(); i++) {
			// 날짜포맷변경
			String orderDate = orderCompleteList.get(i).getOrderDate()
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 
			
			printf("%d. %s %s %s %d\r\n", i + 1, orderCompleteList.get(i).getOrderId(),
					orderDate, orderCompleteList.get(i).getOrderMenuName(),
					orderCompleteList.get(i).getOrderMenuPrice());
		}
		printf("%d. 뒤로가기\r\n", ++i);

		print("\r\n번호 선택: ");
		int choose = nextInt(1, i);
		if (choose == i) { // 뒤로가기 선택했으면
			return;
		}

		// TODO: 별점주기 구현

		// TODO: 기피목록 구현
	}
}
