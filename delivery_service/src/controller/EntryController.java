package controller;

import java.io.IOException;

import model.dao.EntryDao;

public class EntryController {
//	싱글톤
	private EntryController() {}
	private static EntryController instance = new EntryController();
	public static EntryController getInstance () {
		return instance;
	}
	
//	메소드
//	1. 입점신청
	public boolean entryJoin( EntryDto entryDto ) throws IOException {
		boolean result = EntryDao.getInstance().entryJoin(entryDto);
		return result;
	} // f end
	
//	2. 메뉴등록
	public boolean menu() throws IOException {
		
		return true;
	} // f end
}
