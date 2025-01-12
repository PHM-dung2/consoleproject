package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.dao.EntryDao;
import model.dto.EntryDto;
import model.dto.RoadAddressDto;

public class EntryController {
//	싱글톤
	private EntryController() {}
	private static EntryController instance = new EntryController();
	public static EntryController getInstance () {
		return instance;
	}
	
//	메소드
//	1. 입점신청
	public boolean entryJoin( EntryDto entryDto , RoadAddressDto roadAddress ) {
		boolean result = EntryDao.getInstance().entryJoin(entryDto , roadAddress);
		return result;
	} // f end

//	2. 입점 리스트
	public ArrayList<EntryDto> enrtyList() {
		ArrayList<EntryDto> result = EntryDao.getInstance().entryList();
		return result;
	} // f end
	
//	3. 메뉴 리스트
	public ArrayList<EntryDto> menuList() {
		ArrayList<EntryDto> result = EntryDao.getInstance().menuList();
		return result;
	} // f end
	
//	4. 카테고리 리스트
	public ArrayList<EntryDto> cList(  ) throws IOException{
		ArrayList<EntryDto> result = EntryDao.getInstance().cList();
		return result;
	} // f end
	
//	5. 메뉴등록
	public boolean write( EntryDto entryDto ) throws IOException {
		boolean result = EntryDao.getInstance().write(entryDto);
		return result;
	} // f end
	
//	6. 메뉴수정
	public boolean update( int meno , EntryDto entryDto ) throws IOException {
		boolean result = EntryDao.getInstance().update(meno , entryDto);
		return result;
	} // f end
	
//	7. 메뉴삭제
	public boolean delete( int meno ) throws IOException {
		boolean result = EntryDao.getInstance().delete(meno);
		return result;
	} // f end
	
}
