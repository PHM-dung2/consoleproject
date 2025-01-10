package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import controller.EntryController;
import controller.RoadAddressController;
import model.dao.Dao;
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
//	1. 입점 신청
	public void entryJoin() throws IOException {
		println("\n==================     입점신청     ==================");
		print("상호명 : ");			String ename = next();
		print("지점명 : ");			String espot = next();
		
		print("도로명주소 검색: ");
		RoadAddressDto roadAddress = choiceRoadAddress(next());
		print("상세주소: ");
		roadAddress.setDetailAddress(next());
		
		EntryDto entryDto = new EntryDto();
		entryDto.setEname(ename);
		entryDto.setEspot(espot);
		entryDto.setLogInMno(getLoginId());
		
		boolean result = EntryController.getInstance().entryJoin( entryDto , roadAddress);
		if( result ) { println( "입점신청이 완료되었습니다." ); }
		else { println( "입점신청 실패" ); }
		
	} // f end
	
//	2. 입점목록 리스트
	public void entryList() throws IOException {
		ArrayList<EntryDto> result = EntryController.getInstance().enrtyList();
		
		println("\n==================     지점선택     ==================");
		println("번호\t상호명\t\t지점명\t\t입점상태 ");
		for( int i = 0 ; i < result.size() ; i++ ) {
			EntryDto entryDto = result.get(i);
			if( entryDto.getMno() == Dao.getInstance().selectMno(getLoginId()) ) {
				print(entryDto.getEno() + "\t");
				print(entryDto.getEname() + "\t");
				print(entryDto.getEspot() + "\t");
				if( entryDto.getEtype() == 0 ) { println("승인"); }
				else if( entryDto.getEtype() == 1 ){ println("미승인"); }
			}
		} // for end
		
	} // f end
	
//	3. 메뉴 리스트
	public void menuList( int eno ) throws IOException {
		ArrayList<EntryDto> result = EntryController.getInstance().menuList();
		ArrayList<EntryDto> arr = new ArrayList<>();
		
		println("\n==================     메뉴선택     ==================");
		println("번호\t카테고리\t메뉴명\t\t메뉴가격 ");
		for( int i = 0 ; i < result.size() ; i++ ) {
			EntryDto entryDto = result.get(i);
			int count = 1;
			if( entryDto.getEno() == eno ) {
				print(count + "\t");
				print(entryDto.getCno() + "\t\t");
				print(entryDto.getMename() + "\t\t");
				print(entryDto.getMeprice() + "\r\n" );	  // \r 첫 포인터로 이동
				count++;
			}
		} // for end
		
		
	} // f end
	
//	3. 메뉴 등록 페이지
	public void menuIndex() throws IOException {
		while( true ) {
			entryList();
			print("지점번호 선택 : "); 
			int eno = nextInt();
			menuList(eno);
			int choose = nextInt(1,4);
			print("\n1.메뉴등록 2.메뉴수정 3.메뉴삭제 4.뒤로가기");
			switch( choose ) {
				case 1: 
					break;
				case 2: 
					break;
				case 3:
					break;
				case 4:
					break;
			} // switch end
			
		} //  w end
		
	} // f end
	
//	4. 메뉴 등록
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
