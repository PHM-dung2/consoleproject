package controller;

import model.dao.ManageDao;
import model.dto.EntryDto;

public class ManageController {
//	싱글톤
	private ManageController() {}
	private static ManageController instance = new ManageController();
	public static ManageController getInstance() {
		return instance;
	}
	
//	1. 입점승인
	public boolean entryApproval( int eIndex ) {
		boolean result = ManageDao.getInstance().entryApproval(eIndex);
		return result;
	} // f end
	
//	2. 입점 정보 수정
	public boolean update( EntryDto entryDto ) {
		boolean result = ManageDao.getInstance().update(entryDto);
		return result;
	} // f end
	
//	3. 입정 정보 삭제
	public boolean delete( int eIndex ) {
		boolean result = ManageDao.getInstance().delete(eIndex);
		return result;
	} // f end
	
}
