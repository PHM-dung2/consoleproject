package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import controller.EntryController;
import controller.MemberJoinController;
import controller.RoadAddressController;
import model.dto.EntryDto;
import model.dto.MemberDto;
import model.dto.RoadAddressDto;
import network.DSTask;

public class EntryView extends DSTask{
	private MemberDto loginMember;
	
	// 접속된 클라이언트 네트워크 소켓을 받아서 그대로 사용하기 위한 생성자
	public EntryView(Socket clientSocket, BufferedReader reader, PrintWriter writer, MemberDto loginMember) {
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
	
//	메소드
//	1. 입점 페이지 출력
	public void entryStart() throws IOException {
		// 여기서 메뉴 작성 시작하면 됩니다.
		// println() 함수는 System.out.println() 함수와 같습니다.
		// nextInt() 함수는 
			// Scanner scan = new Scanner(System.in);
			// scan.nextInt(); <-- 이 함수와 같습니다.
		// next() 함수도 똑같습니다.
		
		
//		boolean result = EntryController.getInstance().entryJoin(entryDto);
	} // f end
	
//	2. 입점 신청
	public void entryJoin() throws IOException {
		println("==================     입점신청     ==================");
		println("상호명 : ");			String ename = next();
		println("지점명 : ");			String espot = next();
		
		print("도로명주소 검색: ");
		RoadAddressDto roadAddress = choiceRoadAddress(next());
		print("상세주소: ");
		roadAddress.setDetailAddress(next());
		
		EntryDto entryDto = new EntryDto();
		entryDto.setEname(ename);
		entryDto.setEspot(espot);
		entryDto.setLogInMno(getLoginId());
		
		boolean result = EntryController.getInstance().entryJoin( entryDto , roadAddress);
		if( result ) { System.out.println( "입점신청이 완료되었습니다." ); }
		else { System.out.println( "입점신청 실패" ); }
		
	} // f end
	
//	3. 메뉴 등록
	public void menu() throws IOException {
		// 여기서 메뉴 작성 시작하면 됩니다.
		// println() 함수는 System.out.println() 함수와 같습니다.
		// nextInt() 함수는 
			// Scanner scan = new Scanner(System.in);
			// scan.nextInt(); <-- 이 함수와 같습니다.
		// next() 함수도 똑같습니다.
		System.out.println("========== 메뉴신청 ==========");
		System.out.print("상호명\t지점명\t\t지점주소\t\t카테고리");
		
	} // f end
	
	
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
			println(String.format("지번 주소: %s", roadAddressList.get(i).getJibunAddress()));
		}		

		print(": ");
		int choose = nextInt(1, roadAddressList.size());

		// 선택한 도로명 주소 DTO 리턴
		return roadAddressList.get(choose - 1); // ArrayList 인덱스는 0부터 시작하므로 choose 값에 -1 해준다.
	}
}
