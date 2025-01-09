package controller;

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

//	2. 메뉴등록
	public boolean menu() {
		
		return true;
	} // f end
	

	
}
