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
		print("\r\n┌─────────────────────────── 입점신청 ──────────────────────────┐\r\n");
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
		
		boolean result = EntryController.getInstance().entryJoin( entryDto , roadAddress );
		if( result ) { print( "\r\n입점신청이 완료되었습니다.\r\n" ); }
		else { print( "\r\n입점신청 실패\r\n" ); }
		
	} // f end
	
//	2. 입점목록 리스트
	public ArrayList<EntryDto> entryList() throws IOException {
		ArrayList<EntryDto> result = EntryController.getInstance().enrtyList();
		ArrayList<EntryDto> arr = new ArrayList<>();
		
		print("\r\n┌──────────┬──────────────── 지점선택 ───────────────┬──────────┐\r\n");
		print("│" + a.convert("지점번호" , 10) + "│" + a.convert("상호명" , 20) +
				"│" + a.convert("지점명" , 20) + "│" + a.convert("입점상태" , 10) + "│\r\n" );
		int count = 0;
		for( int i = 0 ; i < result.size() ; i++ ) {
			EntryDto entryDto = result.get(i);
			String eType = "";	count++;
			if( entryDto.getMno() == Dao.getInstance().selectMno(getLoginId()) ) {
				if( entryDto.getEtype() == 0 ) { eType = "미승인"; }
				else if( entryDto.getEtype() == 1 ){ eType = "승인"; }
				print("├──────────┼────────────────────┼────────────────────┼──────────┤\r\n");
				print("│" + a.convert( entryDto.getEno()+"" , 10 ) ); 
				print("│" + a.convert( entryDto.getEname() , 20 ) );
				print("│" + a.convert( entryDto.getEspot() , 20 ) );
				print("│" + a.convert( eType+"" , 10 ) +  "│\r\n");
				
				arr.add(entryDto);
				
			} // if end
				
			if( result.size() == count ) {
				print("└──────────┴────────────────────┴────────────────────┴──────────┘\r\n");
			} // if end
			
		} // for end
		return arr;
	} // f end
	
//	3. 메뉴 등록 페이지
	public void menuIndex() throws IOException {
			ArrayList<EntryDto> arr = entryList();
			print("\r\n지점번호 선택 : "); 
//			입력 유효성검사
			int eno = nextInt(1,arr.size());
			while( true ) {
				print("\r\n┌────────────────────────── 메뉴 페이지 ────────────────────────┐\r\n");
				print("\r\n1.메뉴등록 2.메뉴목록 3.메뉴수정 4.메뉴삭제 5.뒤로가기 ");
				int choose = nextInt(1,5);
				switch( choose ) {
				case 1: 
					write(eno);
					break;
				case 2: 
					menuList(eno);
					break;	
				case 3: 
					update(eno);
					break;
				case 4:
					delete(eno);
					break;
				case 5:
					return;
				} // switch end
				
			} // w end
		
	} // f end
	
//	4. 메뉴 리스트
	public ArrayList<Integer> menuList( int eno ) throws IOException {
		ArrayList<EntryDto> result = EntryController.getInstance().menuList();
		ArrayList<Integer> arr = new ArrayList<>();
		
		print("\r\n┌──────────┬────────────────── 메뉴 ─────────────────┬──────────┐\r\n");
		print("│" + a.convert("번호" , 10) + "│" + a.convert("카테고리" , 20) + "│" + a.convert("메뉴명" , 20) + 
				"│" + a.convert("메뉴가격" , 10) + "│" + "\r\n");
		
		int num = 1;	int count = 0;
		for( int i = 0 ; i < result.size() ; i++ ) {
			EntryDto entryDto = result.get(i);	count++;
			if( entryDto.getEno() == eno ) {
				arr.add(entryDto.getMeno());
				print("├──────────┼────────────────────┼────────────────────┼──────────┤\r\n");
				print( "│" + a.convert( num+"" , 10) );
				print( "│" + a.convert( entryDto.getCname() , 20) );
				print( "│" + a.convert( entryDto.getMename() , 20) );
				print( "│" + a.convert( entryDto.getMeprice()+"" , 10) + "│\r\n"  );
				num++;
			} // if end
			
			if( result.size() == count ) {
				print("└──────────┴────────────────────┴────────────────────┴──────────┘\r\n");
			} // if end
			
		} // for end
		return arr;
		
	} // f end
	
//	5. 메뉴번호 호출 메소드
	public int meno( int eno ) throws IOException{
		ArrayList<Integer> arr = menuList(eno);
		print("\r\n메뉴번호 선택 : "); 
		int mIndex = nextInt(1 , arr.size());
		int result = arr.get(mIndex-1);
		return result;
	} // f end
	
//	6. 카테고리 리스트
	public void cList() throws IOException{
		print("\r\n┌──────────┬ 카테고리 ──────────┐\r\n");
		print("│" + a.convert("번호" , 10) + "│" + a.convert("카테고리명" , 20) + "│\r\n");
		
		ArrayList<EntryDto> result = EntryController.getInstance().cList();
		int count = 0;
		for( int i = 0 ; i < result.size() ; i++ ) {
			EntryDto entryDto = result.get(i);	count++;
			print("├──────────┼────────────────────┤\r\n");
			print( "│" + a.convert( entryDto.getCno()+"" , 10) );
			print( "│" + a.convert( entryDto.getCname() , 20) + "│\r\n" );
		} // for end
		
		if( result.size() == count ) {
			print("└──────────┴────────────────────┘\r\n");
		} // if end
		
	} // f end
	
//	7. 메뉴등록
	public void write( int eno ) throws IOException {
		print("\r\n┌─────────────────────────── 메뉴등록 ──────────────────────────┐\r\n");
		cList();
		print("\r\n카테고리 번호 : ");		int cno = nextInt();
		print("메뉴명 : ");			String mename = next();
		print("메뉴 가격 : ");		int meprice = nextInt();
		EntryDto entryDto = new EntryDto( mename , meprice , cno , eno );
		
		boolean result = EntryController.getInstance().write( entryDto );		
		if( result ) { print("메뉴등록이 완료되었습니다.\r\n"); } 
		else { print("메뉴등록 실패\r\n"); } 
	} // f end
	
//	8. 메뉴수정
	public void update( int eno ) throws IOException {
		print("\r\n┌─────────────────────────── 메뉴수정 ──────────────────────────┐\r\n");
		int meno = meno( eno );
		cList();
		print("\r\n수정할 카테고리 번호 : ");		int cno = nextInt();
		print("수정할 메뉴명 : ");			String mename = next();
		print("수정할 메뉴 가격 : ");		int meprice = nextInt();
		EntryDto entryDto = new EntryDto( mename , meprice , cno , eno );
		
		print("\r\n정말 수정하시겠습니까?\r\n");
		print("1. 예 2. 아니요 ");
		int choose = nextInt(1,2);
		if( choose == 2 ) { return; }
		boolean result = EntryController.getInstance().update(meno , entryDto);
		if( result ) { print("\r\n메뉴수정이 완료되었습니다.\r\n"); } 
		else { print("\r\n메뉴수정 실패\r\n"); }
		
	} // f end
	
//	9. 메뉴삭제
	public void delete( int eno ) throws IOException {
		print("\r\n┌─────────────────────────── 메뉴삭제 ──────────────────────────┐\r\n");
		int meno = meno( eno );
		print("\r\n정말 삭제하시겠습니까?\r\n");
		print("1. 예 2. 아니요 ");
		int choose = nextInt(1,2);
		if( choose == 2 ) { return; }
		boolean result = EntryController.getInstance().delete(meno);
		if( result ) { print("\r\n메뉴삭제가 완료되었습니다.\r\n"); } 
		else { print("\r\n메뉴삭제 실패\r\n"); }
	} // f end
	
	
//	주소 불러오기 api
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

		print("----- 검색된 주소 확인후 맞는 주소 번호 선택해주세요.\r\n");
		for (int i = 0; i < roadAddressList.size(); i++) {
			print(String.format("(%d) 주소\r\n", i + 1));
			print(String.format("우편번호: %s\r\n", roadAddressList.get(i).getZipCode()));
			print(String.format("도로명 주소: %s\r\n", roadAddressList.get(i).getRoadAddress()));
			print(String.format("지번 주소: %s\r\n", roadAddressList.get(i).getJibunAddress()));
		}		

		print(": ");
		int choose = nextInt(1, roadAddressList.size());

		// 선택한 도로명 주소 DTO 리턴
		return roadAddressList.get(choose - 1); // ArrayList 인덱스는 0부터 시작하므로 choose 값에 -1 해준다.
	}
	
	
	
	
	
	
}
