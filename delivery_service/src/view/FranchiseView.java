package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

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
			print("\r\n┌──────────────────────── 입점회원 페이지 ──────────────────────┐\r\n");
			print("\r\n1.입점신청 2.메뉴관리 3.주문콜대기 4.주문완료목록 5.로그아웃 ");
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
		print("\r\n주문 대기중입니다... (q 입력시 메뉴로 돌아갑니다.)");
		while (!(next().equals("q"))) {
		}
	}

	public void orderCompleteList() throws IOException {
		ArrayList<OrderCompleteDto> orderCompleteList = new ArrayList<>();

		orderCompleteList = FranchiseController.getInstance().getOrderCompleteList(getLoginMno());

		// 주문일 기준 오름차순 정렬
		orderCompleteList.sort(Comparator.comparing(OrderCompleteDto::getOrderDate));

		if (orderCompleteList.size() == 0) {
			print("\r\n주문완료 목록이 없습니다.");
			return;
		}

		print("\r\n------------------      주문완료목록      ------------------");
		print("번호 | 주문자 | 주문일 | 주문점 | 주문메뉴 | 주문가격");
		int i;
		for (i = 0; i < orderCompleteList.size(); i++) {
			// 날짜포맷변경
			String orderDate = orderCompleteList.get(i).getOrderDate()
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			printf("%d. | %s | %s | %s | %s | %d\r\n", i + 1, orderCompleteList.get(i).getOrderId(), orderDate,
					orderCompleteList.get(i).getOrderEntryName(), orderCompleteList.get(i).getOrderMenuName(),
					orderCompleteList.get(i).getOrderMenuPrice());
		}
		printf("%d. 뒤로가기\r\n", ++i);

		print("\r\n번호 선택: ");
		int choose = nextInt(1, i);
		if (choose == i) { // 뒤로가기 선택했으면
			return;
		}

		// 별점주기
		OrderCompleteDto dto = orderCompleteList.get(choose - 1); // 선택한 주문 정보

		printf("\r\n'%s' 회원 별점주기 or 기피신청\r\n", dto.getOrderId());
		print("/r/n1. 별점주기 2. 기피신청 3. 처음으로 ");
		switch (nextInt(1, 3)) {
		case 1:
			// 별점주기
			print("별점(1 ~ 5): ");
			if (FranchiseController.getInstance().insertStarPoint(nextInt(1, 5), dto)) {
				println("\r\n별점주기 성공하였습니다.");
			} else {
				println("\r\n** 별점주기 실패 **");
			}
			break;
		case 2:
			int result = FranchiseController.getInstance().insertDodgeMember(dto);
			switch (result) {
				case 0:
					printf("\r\n'%s' 가맹점에서 '%s' 회원에 대해 기피신청 완료되었습니다.", dto.getOrderEntryName(), dto.getOrderId());
					break;
				case 1:
					println("\r\n** 기피신청 실패 **");
					break;
				case 2:
					println("\r\n 이미 기피신청된 회원입니다.");					
			}			
			break;
		case 3:
			return;
		}
	}
}
