package model.dao;

import java.io.IOException;
import java.util.ArrayList;

import model.dto.EntryDto;


public class EntryDao {
//	싱글톤
	private EntryDao() {}
	private static EntryDao instance = new EntryDao();
	public static EntryDao getInstance () {
		return instance;
	} 
	
	private ArrayList<EntryDto> entryDB = new ArrayList<>();
	
//	메소드
//	1. 입점신청
	public boolean entryJoin( EntryDto entryDto ) throws IOException {
		
	} // f end
	
//	2. 메뉴등록
	public boolean menu() throws IOException {
		
	} // f end
	
}

