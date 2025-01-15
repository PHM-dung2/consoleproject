package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import controller.EntryController;
import controller.ManageController;
import model.dto.EntryDto;
import network.DSTask;

public class ManageView extends DSTask{
	public ManageView(Socket clientSocket , BufferedReader reader, PrintWriter writer) {
		this.clientSocket = clientSocket;
		this.reader = reader;
		this.writer = writer;	
	}
	
//	1. 미승인 입점목록
	public void nonentryList() throws IOException {
		ArrayList<EntryDto> result = EntryController.getInstance().enrtyList();
		
		println("지점번호     상호명     지점명     입점상태" );
		for( int i = 0 ; i < result.size() ; i++ ) {
			EntryDto entryDto = result.get(i);
			if( entryDto.getEtype() == 0 ) {
				String eType = "미승인";
				print(entryDto.getEno() + "     "); 
				print(entryDto.getEname() + "     ");
				print(entryDto.getEspot() + "     ");
				println(eType + "     ");
			}
		} // for end
		
	} // f end
	
//	2. 승인 입점목록
	public void entryList() throws IOException {
		ArrayList<EntryDto> result = EntryController.getInstance().enrtyList();
		
		println("지점번호     상호명     지점명     입점상태" );
		for( int i = 0 ; i < result.size() ; i++ ) {
			EntryDto entryDto = result.get(i);
			if( entryDto.getEtype() == 1 ) {
				String eType = "승인";
				print(entryDto.getEno() + "     "); 
				print(entryDto.getEname() + "     ");
				print(entryDto.getEspot() + "     ");
				println(eType + "     ");
			}
		} // for end
		
	} // f end
	
//	3. 입점신청 목록 페이지
	public void nonEntry() throws IOException {
		while(true) {
			println("\n==================     입점신청 목록     ==================");
			nonentryList();
			
			print("\r\n1.번호선택 2.뒤로가기 ");
			int choose = nextInt(1,2);
			
			switch( choose ) {
				case 1:
					nonEntryChoice();
					break;
				default:
					return;
			} // s end
		} // w end
		
	}// f end
	
//	4. 입점목록 페이지
	public void entry() throws IOException {
		while(true) {
			println("\n==================     입점 목록     ==================");
			entryList();
			
			print("\r\n1.번호선택 2.뒤로가기 ");
			int choose = nextInt(1,2);
			
			switch( choose ) {
				case 1:
					entryChoice();
					break;
				default:
					return;
			} // s end
		} // w end
		
	}// f end
	
//	5. 입접신청목록 번호선택 페이지
	public void nonEntryChoice() throws IOException {
		
		while( true ) {
			print("\n번호선택 : ");
			int eIndex = nextInt();
			print("\n1.입점 승인 2.입점 거절 3.뒤로가기");
			int choose = nextInt(1,3);
			
			switch( choose ) {
			case 1:
				entryApproval(eIndex);
				break;
			case 2:
				entryRefusal(eIndex);
				break;	
			} // s end
			return;
		} // w end
		
	} // f end
	
//	6. 입접목록 번호선택 페이지
	public void entryChoice() throws IOException {
		
		while( true ) {
			print("\n번호선택 : ");
			int eIndex = nextInt();
			print("\n1.입점수정 2.입점삭제 3.뒤로가기");
			int choose2 = nextInt(1,3);
			
			switch( choose2 ) {
			case 1:
				update( eIndex );
				break;
			case 2:
				delete( eIndex );
				break;	
			} // s end
			return;
		} // w end
		
	} // f end
	
//	7. 입점승인
	public void entryApproval( int eIndex ) throws IOException {
		println("\n입점 승인하시겠습니까?");
		print("1.예 2.아니오 ");
		int choose = nextInt(1,2);
		switch(choose) {
			case 1:
				boolean result = ManageController.getInstance().entryApproval(eIndex);
				if( result ) { println("입점 승인되었습니다"); }
				else { println("입점 승인 실패"); }
				break;
		} // s end
	} // f end
	
//	8. 입점거절
	public void entryRefusal( int eIndex ) throws IOException {
		println("\n입점 거절하시겠습니까?");
		print("1.예 2.아니오 ");
		nextInt(1,2);
		
		return;
	} // f end
	
//	9. 입점 정보 수정
	public void update( int eIndex ) throws IOException {
		println("\n==================     입점 정보 수정     ==================");
		print("상호명 : ");			String ename = next();
		print("지점명 : ");			String espot = next();
		println("상태 변경을 하시겠습니까?");	
		print("1.예 2.아니오");		int etype = nextInt();
		if( etype == 1 ) { etype = 0; }
		
		println("\n입점 정보를 수정하시겠습니까?");
		print("1.예 2.아니오 ");
		int choose = nextInt(1,2);
		if( choose == 2 ) { return; }
		
		EntryDto entryDto = new EntryDto();
		entryDto.setEname(ename);
		entryDto.setEspot(espot);
		entryDto.setEtype(etype);
		entryDto.setEno(eIndex);
		
		boolean result = ManageController.getInstance().update(entryDto);
		if(result) { println("입점 정보 수정이 완료되었습니다."); }
		else { println("입점 정보 수정 실패"); }
	} // f end
	
//	10. 입정 정보 삭제
	public void delete( int eIndex ) throws IOException {
		println("\n==================     입점 정보 삭제     ==================");
		println("\n입점 정보를 삭제하시겠습니까?");
		print("1.예 2.아니오 ");
		int choose = nextInt(1,2);
		switch(choose) {
			case 1:
				boolean result = ManageController.getInstance().delete(eIndex);
				if( result ) { println("입점 정보가 삭제되었습니다"); }
				else { println("입점 정보 삭제 실패"); }
				break;
		} // s end
		return;
	} // f end
	
	
}// class end
