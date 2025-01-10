package controller;

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
	}

	
}
