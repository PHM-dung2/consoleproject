package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import controller.MemberJoinController;
import controller.RoadAddressController;
import network.DSTask;

import model.dto.MemberDto;

import model.dto.RoadAddressDto;

public class MemberJoinView extends DSTask {
	public MemberJoinView(Socket clientSocket, BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;
	}

	public void index() throws IOException, InterruptedException {
		MemberJoinController memberJoinController = MemberJoinController.getInstance();

		// TODO: 입력값 유효성 확인		
		println("\r\n------------------      회원가입신청      ------------------");
		print("ID: ");
		String id = next();
		while (memberJoinController.checkID(id)) {
			println("중복 ID 입니다.");
			print("ID: ");
			id = next();
		}
		print("Password: ");
		String password = next();
		print("이름: ");
		String name = next();
		print("전화번호: ");
		String telno = next();		
		print("도로명주소 검색: ");
		RoadAddressDto roadAddress = choiceRoadAddress(next());
		print("상세주소: ");
		roadAddress.setDetailAddress(next());
		print("가입유형(1: 관리자, 2: 가맹회원, 3: 일반회원): ");
		int type = nextInt(1, 3);

		// 입력받은 값들을 MemberDto 에 넣는다.
		MemberDto member = new MemberDto(id, password, name, telno, roadAddress, type);

		// 컨트롤러 싱글턴 객체를 통해 DB 에 insert 한다. insert 성공하면 true 다.
		if (MemberJoinController.getInstance().join(member)) {
			println("\r\n가입 성공하였습니다!");
		} else {
			println("\r\n** 가입 실패 **");
		}
	}
	
	private RoadAddressDto choiceRoadAddress(String keyword) throws IOException {
		RoadAddressDto roadAddress;
		
		roadAddress = choiceRoadAddressInter(keyword);
		if (roadAddress != null) return roadAddress;
		
		// 올바른 검색 주소 입력할때까지 무한 루프
		while ( (roadAddress = choiceRoadAddressInter(next())) == null) {			
		}		
		
		return roadAddress;
	}

	private RoadAddressDto choiceRoadAddressInter(String keyword) throws IOException {
		ArrayList<RoadAddressDto> roadAddressList = RoadAddressController.getInstance().getRoadAddress(keyword);
		
		if (roadAddressList.size() == 0) {
			print("도로명 주소가 없습니다. 검색어를 다시 입력해주세요 : ");
			return null;
		}

		println("----- 검색된 주소 확인후 맞는 주소 번호 선택해주세요.");
		for (int i = 0; i < roadAddressList.size(); i++) {
			println(String.format("(%d) 주소", i + 1));
			println(String.format("우편번호: %s", roadAddressList.get(i).getZipCode()));
			println(String.format("도로명 주소: %s", roadAddressList.get(i).getRoadAddress()));
			println(String.format("지번 주소: %s\r\n", roadAddressList.get(i).getJibunAddress()));
		}		

		print(": ");
		int choose = nextInt(1, roadAddressList.size());

		// 선택한 도로명 주소 DTO 리턴
		return roadAddressList.get(choose - 1); // ArrayList 인덱스는 0부터 시작하므로 choose 값에 -1 해준다.
	}
}
