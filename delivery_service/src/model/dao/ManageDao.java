package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dto.EntryDto;

public class ManageDao extends Dao{
	//	싱글톤
	private ManageDao() {}
	private static ManageDao instance = new ManageDao();
	public static ManageDao getInstance() {
		return instance;
	}
	
//	1. 입점승인
	public boolean entryApproval( int eIndex ) {
		try {
			String sql = "update entry set etype = 1 where eno = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, eIndex);
			int count = ps.executeUpdate();
			if( count == 1) { return true; }
		}catch( SQLException e ) { System.out.println( e ); }
		return false;
	} // f end
	
//	2. 입점 정보 수정
	public boolean update( EntryDto entryDto ) {
		try {
			String sql = "update entry set ename = ? , espot = ? , etype = ? "
					+ "where eno = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, entryDto.getEname());
				ps.setString(2, entryDto.getEspot());
				ps.setInt(3, entryDto.getEtype());
				ps.setInt(4, entryDto.getEno());
			int count = ps.executeUpdate();
			if( count == 1) { return true; }
		}catch( SQLException e ) { System.out.println( e ); }
		return false;
	} // f end
	
//	3. 입정 정보 삭제
	public boolean delete( int eIndex ) {
		return false;
	} // f end
	
}
